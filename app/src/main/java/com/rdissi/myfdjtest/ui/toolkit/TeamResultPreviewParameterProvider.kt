package com.rdissi.myfdjtest.ui.toolkit

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.rdissi.myfdjtest.ui.team.TeamViewModel.UiState

class TeamResultPreviewParameterProvider : PreviewParameterProvider<UiState> {
    override val values = sequenceOf(
        UiState.Loading,
        UiState.Success(MockDataSource.getFirstMockTeam()),
        UiState.Error("An error occurred")
    )
}