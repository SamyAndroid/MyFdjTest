package com.rdissi.myfdjtest.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rdissi.myfdjtest.domain.model.Team
import com.rdissi.myfdjtest.ui.toolkit.MockDataSource

@Composable
fun TeamGrid(
    teams: List<Team>,
    onTeamSelected: (Team) -> Unit,
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(150.dp),
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(items = teams, key = { it.id }) { team ->
            TeamItemGrid(
                team = team,
                onTeamSelected = onTeamSelected,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TeamGridPreview() {
    TeamGrid(
        teams = MockDataSource.getMockTeams(),
        onTeamSelected = {},
    )
}
