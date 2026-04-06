package com.rdissi.myfdjtest.ui.component

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstrainScope
import androidx.constraintlayout.compose.ConstrainedLayoutReference
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintLayoutScope
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import coil.request.ImageRequest
import com.rdissi.myfdjtest.R

@Composable
fun ConstraintLayoutScope.HeaderImage(
    modifier: Modifier = Modifier,
    imageUrl: String,
    reference: ConstrainedLayoutReference,
    constraint: ConstrainScope.() -> Unit,
) {
    val context = LocalContext.current
    val ratio by remember { mutableFloatStateOf(16F / 9) }

    ElevatedCard(
        shape = MaterialTheme.shapes.small,
        colors = CardDefaults.elevatedCardColors(containerColor = Color.White),
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 4.dp),
        modifier =
        modifier
            .fillMaxWidth()
            .background(Color.White)
            .constrainAs(reference, constraint),
    ) {
        AsyncImage(
            model =
            ImageRequest
                .Builder(context)
                .data(imageUrl)
                .crossfade(true)
                .build(),
            placeholder = painterResource(R.drawable.placeholder_badge_rect),
            error = painterResource(R.drawable.placeholder_badge_rect),
            contentDescription = null,
            contentScale = ContentScale.Fit,
            modifier =
            modifier
                .padding(16.dp)
                .fillMaxWidth()
                .aspectRatio(ratio),
            onError = { error: AsyncImagePainter.State.Error ->
                Log.d("AsyncImage", "HeaderImage error: " + error.result.throwable)
            },
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HeaderImagePreview() {
    ConstraintLayout {
        val ref = createRef()
        HeaderImage(
            imageUrl = "",
            reference = ref,
        ) {}
    }
}

