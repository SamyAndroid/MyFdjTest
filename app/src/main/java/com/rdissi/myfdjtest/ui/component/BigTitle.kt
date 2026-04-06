package com.rdissi.myfdjtest.ui.component

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstrainScope
import androidx.constraintlayout.compose.ConstrainedLayoutReference
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintLayoutScope
import com.rdissi.myfdjtest.ui.toolkit.MockDataSource

@Composable
fun ConstraintLayoutScope.BigTitle(
    modifier: Modifier = Modifier,
    title: String,
    reference: ConstrainedLayoutReference,
    constraint: ConstrainScope.() -> Unit
) {
    Text(
        text = title,
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold,
        color = Color.Black,
        maxLines = 2,
        overflow = TextOverflow.Ellipsis,
        modifier = modifier.constrainAs(reference, constraint)
    )
}


@Preview(showBackground = true)
@Composable
fun BigTitlePreview() {
    ConstraintLayout{
        val ref = createRef()
        BigTitle(
            title = MockDataSource.getFirstMockTeam().name,
            reference = ref
        ) {}
    }
}