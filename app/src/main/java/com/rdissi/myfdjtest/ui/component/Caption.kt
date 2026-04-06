package com.rdissi.myfdjtest.ui.component

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstrainScope
import androidx.constraintlayout.compose.ConstrainedLayoutReference
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintLayoutScope
import com.rdissi.myfdjtest.ui.toolkit.MockDataSource
import com.rdissi.myfdjtest.domain.model.League
import com.rdissi.myfdjtest.domain.model.Team

@Composable
fun ConstraintLayoutScope.Caption(
    modifier: Modifier = Modifier,
    team: Team,
    reference: ConstrainedLayoutReference,
    constraint: ConstrainScope.() -> Unit
) {
    Text(
        text = team.league2,
        fontSize = 12.sp,
        fontWeight = FontWeight.Normal,
        color = Color.LightGray,
        modifier = modifier.constrainAs(reference, constraint)
    )
}

@Preview(showBackground = true)
@Composable
fun CaptionPreview() {
    ConstraintLayout{
        val ref = createRef()
        Caption(
            team = MockDataSource.getFirstMockTeam(),
            reference = ref
        ) {}
    }
}