package com.rdissi.myfdjtest.ui.toolkit

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.rdissi.myfdjtest.domain.model.Team

class TeamCardPreviewParameterProvider : PreviewParameterProvider<Team> {
    override val values = sequenceOf(
        MockDataSource.getFirstMockTeam()
    )
}