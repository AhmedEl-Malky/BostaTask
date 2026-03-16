package com.malky.bostatask.presentation.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import com.malky.bostatask.R
import kotlin.math.absoluteValue

@Composable
fun ScreenshotsPager(
    modifier: Modifier = Modifier,
) {
    val screenshotUrls = listOf(
        painterResource(R.drawable.gow),
        painterResource(R.drawable.gow),
        painterResource(R.drawable.gow),
        painterResource(R.drawable.gow),
        painterResource(R.drawable.gow),
        painterResource(R.drawable.gow),
        painterResource(R.drawable.gow),
    )

    val pagerState = rememberPagerState { screenshotUrls.size }

    HorizontalPager(
        modifier = modifier,
        state = pagerState,
        contentPadding = PaddingValues(horizontal = 48.dp),
        pageSpacing = 16.dp,
    ) { page ->
        val pageOffset = (
                (pagerState.currentPage - page) + pagerState.currentPageOffsetFraction).absoluteValue

        // Scale effect: Active page is 1.0, others are 0.85
        val scale = lerp(
            start = 0.85f,
            stop = 1f,
            fraction = 1f - pageOffset.coerceIn(0f, 1f)
        )

        Card(
            modifier = Modifier
                .graphicsLayer {
                    scaleX = scale
                    scaleY = scale
                }
                .fillMaxWidth()
                .aspectRatio(16 / 9f),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color.Transparent),
            border = if (page == pagerState.currentPage) {
                BorderStroke(2.dp, Color.White)
            } else {
                BorderStroke(1.dp, Color.White.copy(alpha = 0.2f))
            }
        ) {
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = screenshotUrls[page],
                contentDescription = "Screenshot $page",
                contentScale = ContentScale.Crop,
            )
        }
    }
}

@Preview
@Composable
private fun PreviewScreenShotsPager() {
    ScreenshotsPager()
}