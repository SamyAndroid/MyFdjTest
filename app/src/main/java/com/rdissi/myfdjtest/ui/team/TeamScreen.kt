package com.rdissi.myfdjtest.ui.team

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.rdissi.myfdjtest.ui.component.SideEffectWithLifeCycle
import com.rdissi.myfdjtest.ui.component.showToast
import com.rdissi.myfdjtest.ui.error.ErrorScreen
import com.rdissi.myfdjtest.ui.team.TeamViewModel.UiState
import com.rdissi.myfdjtest.ui.toolkit.TeamResultPreviewParameterProvider

@Composable
fun TeamScreen(
    modifier: Modifier = Modifier,
    leagueId: String,
    teamViewModel: TeamViewModel = hiltViewModel()
) {

    val uiState by teamViewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current
    val lifecycleOwner = androidx.lifecycle.compose.LocalLifecycleOwner.current

    LaunchedEffect(key1 = teamViewModel) {
        teamViewModel.uiEvent.collect { event ->
            when (event) {
                is TeamViewModel.UiEvent.Toast -> showToast(context, event.message)
            }
        }
    }
    SideEffectWithLifeCycle(
        lifecycleOwner = lifecycleOwner,
        onStart = { teamViewModel.getTeamById(leagueId) }
    )
    TeamResult(
        modifier = modifier,
        uiState = uiState
    )
}

@Composable
fun TeamResult(
    modifier: Modifier = Modifier,
    uiState: UiState,
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        when (uiState) {
            UiState.Loading -> {
                LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
            }
            is UiState.Success -> {
                TeamDetails(team = uiState.team)
            }
            is UiState.Error -> {
                ErrorScreen(message = "Team not found")
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun TeamScreenPreview(
    @PreviewParameter(TeamResultPreviewParameterProvider::class) uiState: UiState
) {
    TeamResult(uiState = uiState)
}