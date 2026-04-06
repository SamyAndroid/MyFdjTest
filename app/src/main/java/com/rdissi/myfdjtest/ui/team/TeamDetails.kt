package com.rdissi.myfdjtest.ui.team

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.rdissi.myfdjtest.R
import com.rdissi.myfdjtest.common.capitalized
import com.rdissi.myfdjtest.domain.model.Team
import com.rdissi.myfdjtest.ui.component.BigTitle
import com.rdissi.myfdjtest.ui.component.HeaderImage
import com.rdissi.myfdjtest.ui.component.Tag
import com.rdissi.myfdjtest.ui.theme.LightGrey
import com.rdissi.myfdjtest.ui.toolkit.MockDataSource

@Composable
fun TeamDetails(
    team: Team,
) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .background(LightGrey)
            .padding(horizontal = 16.dp)
            .verticalScroll(state = rememberScrollState()),
    ) {
        val (headerImage, tag, title, caption, since, description) = createRefs()

        HeaderImage(imageUrl = team.badgeUrl, reference = headerImage) {
            top.linkTo(parent.top, margin = 16.dp)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            width = Dimension.fillToConstraints
        }

        Tag(label = team.name, reference = tag) {
            start.linkTo(headerImage.start, margin = 16.dp)
            bottom.linkTo(headerImage.bottom)
            top.linkTo(headerImage.bottom)
        }

        BigTitle(title = team.league, reference = title) {
            start.linkTo(headerImage.start)
            top.linkTo(tag.bottom, margin = 16.dp)
            end.linkTo(headerImage.end)
            width = Dimension.fillToConstraints
        }

        Text(
            text = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        color = Color.Black,
                        fontSize = 14.sp
                    )
                ) {
                    append(stringResource(id = R.string.stadium))
                }
                withStyle(
                    style = SpanStyle(
                        fontSize = 14.sp,
                        color = Color(red = 65, green = 160, blue = 253)
                    )
                ) {
                    append(" ${team.stadium.capitalized()}")
                }

            },
            modifier = Modifier.constrainAs(caption) {
                start.linkTo(headerImage.start)
                top.linkTo(title.bottom, margin = 4.dp)
                end.linkTo(headerImage.end, margin = 16.dp)
                width = Dimension.fillToConstraints
            },
        )

        Text(
            text = stringResource(id = R.string.since, team.formedYear),
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.Black,
            modifier = Modifier.constrainAs(since) {
                start.linkTo(headerImage.start)
                top.linkTo(caption.bottom, margin = 8.dp)
                end.linkTo(headerImage.end, margin = 16.dp)
                width = Dimension.fillToConstraints
            },
        )

        Text(
            text = team.description,
            fontSize = 18.sp,
            fontWeight = FontWeight.Normal,
            color = Color.Black,
            modifier = Modifier.constrainAs(description) {
                start.linkTo(headerImage.start)
                top.linkTo(since.bottom, margin = 16.dp)
                end.linkTo(headerImage.end, margin = 16.dp)
                width = Dimension.fillToConstraints
            },
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun TeamPagePreview() {
    TeamDetails(
        team = MockDataSource.getFirstMockTeam()
    )
}