package com.rdissi.myfdjtest.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.rdissi.myfdjtest.R
import com.rdissi.myfdjtest.domain.model.Team
import com.rdissi.myfdjtest.ui.toolkit.TeamCardPreviewParameterProvider

@Composable
fun TeamItemGrid(
    modifier: Modifier = Modifier,
    team: Team,
    onTeamSelected: (Team) -> Unit = {}
) {
    val context = LocalContext.current
    ElevatedCard(
        shape = MaterialTheme.shapes.small,
        colors = CardDefaults.elevatedCardColors(containerColor = Color.White),
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 4.dp),
        modifier = modifier
            .fillMaxWidth()
            .background(Color.White)
            .clickable { onTeamSelected(team) },
    ) {
        Column(
            modifier = Modifier.padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            AsyncImage(
                model = ImageRequest.Builder(context)
                    .data(team.badgeUrl)
                    .crossfade(true)
                    .build(),
                placeholder = painterResource(R.drawable.placeholder_badge_square),
                error = painterResource(R.drawable.placeholder_badge_square),
                contentDescription = null,
                contentScale = ContentScale.Inside,
                modifier = modifier
                    .fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = team.name,
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center,
                maxLines = 1,
                softWrap = false,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun TeamCardPreview(
    @PreviewParameter(TeamCardPreviewParameterProvider::class) team: Team
) {
    TeamItemGrid(team = team)
}
