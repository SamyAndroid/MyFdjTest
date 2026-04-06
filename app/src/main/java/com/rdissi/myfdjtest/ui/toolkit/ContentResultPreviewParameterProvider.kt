package com.rdissi.myfdjtest.ui.toolkit

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.rdissi.myfdjtest.ui.content.ContentViewModel.UiState

class ContentResultPreviewParameterProvider : PreviewParameterProvider<UiState> {
    override val values = sequenceOf(
        UiState(isLoading = true),
        UiState(leagues = MockDataSource.getMockLeagues()),
        UiState(error = "An error occurred")
    )
}