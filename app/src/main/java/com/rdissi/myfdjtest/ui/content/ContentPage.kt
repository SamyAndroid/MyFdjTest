package com.rdissi.myfdjtest.ui.content

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.rdissi.myfdjtest.domain.model.League
import com.rdissi.myfdjtest.domain.model.Team
import com.rdissi.myfdjtest.ui.component.AutoCompleteTextField
import com.rdissi.myfdjtest.ui.component.TeamGrid
import com.rdissi.myfdjtest.ui.content.ContentViewModel.UiState
import com.rdissi.myfdjtest.ui.error.ErrorScreen
import com.rdissi.myfdjtest.ui.toolkit.ContentResultPreviewParameterProvider

@Composable
fun ContentPage(
    modifier: Modifier = Modifier,
    contentViewModel: ContentViewModel = hiltViewModel(),
    onTeamSelected: (Team) -> Unit,
) {
    val uiState by contentViewModel.uiState.collectAsStateWithLifecycle()
    ContentResult(
        modifier = modifier,
        uiState = uiState,
        onTeamSelected = onTeamSelected,
        onQueryChange = { query ->
            contentViewModel.onQueryChange(query)
        },
        onCancelSearch = {
            contentViewModel.cancel()
        },
        onLeagueSelected = { league ->
            contentViewModel.getTeamsByLeague(league.name)
        }
    )
}

@Composable
private fun ContentResult(
    modifier: Modifier = Modifier,
    uiState: UiState,
    onQueryChange: (String) -> Unit = {},
    onCancelSearch: () -> Unit = {},
    onLeagueSelected: (League) -> Unit = {},
    onTeamSelected: (Team) -> Unit = {},
) {
    Column(
        modifier =
            modifier
                .fillMaxSize()
                .background(Color.White),
        verticalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        AutoCompleteTextField(
            query = uiState.query,
            items = uiState.leagues,
            onQueryChange = onQueryChange,
            onLeagueSelected = onLeagueSelected,
            onCancelSearch = onCancelSearch,
        )

        if (uiState.isLoading) {
            LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
        }

        if (uiState.teams.isNotEmpty()) {
            TeamGrid(
                teams = uiState.teams,
                onTeamSelected = onTeamSelected,
            )
        }
        if (uiState.error != null) {
            ErrorScreen(uiState.error)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ContentResultPreview(
    @PreviewParameter(ContentResultPreviewParameterProvider::class) uiState: UiState,
) {
    ContentResult(uiState = uiState)
}
