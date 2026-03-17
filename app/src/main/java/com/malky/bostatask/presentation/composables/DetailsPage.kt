package com.malky.bostatask.presentation.composables

import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.malky.bostatask.R
import com.malky.bostatask.domain.GameDetails
import com.malky.bostatask.ui.theme.AccentYellow
import com.malky.bostatask.ui.theme.Background
import com.malky.bostatask.ui.theme.TextPrimary
import com.malky.bostatask.ui.theme.TextSecondary

@Composable
fun DetailsPage(
    game: GameDetails?
) {
    //region internal states
    val screenshotsPagerState = rememberPagerState { game?.screenshots?.size ?: 0 }
    //endregion
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Background),
    ) {
        Crossfade(
            targetState = game?.screenshots?.getOrNull(screenshotsPagerState.currentPage)?.url,
            animationSpec = tween(durationMillis = 550),
        ) { imageUrl ->
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.25f),
                model = imageUrl ?: game?.coverUrl,
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
        }
        Text(
            modifier = Modifier.padding(horizontal = 16.dp),
            text = game?.name ?: "",
            style = MaterialTheme.typography.headlineLarge.copy(
                color = TextPrimary,
                fontWeight = FontWeight.Bold
            ),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Icon(
                    modifier = Modifier.size(20.dp),
                    painter = painterResource(id = R.drawable.rating_star),
                    contentDescription = null,
                    tint = AccentYellow
                )
                Text(
                    text = game?.rating.toString() + if (game?.topRating != null) "/${game.topRating}" else "",
                    style = MaterialTheme.typography.titleMedium.copy(
                        color = TextPrimary,
                        fontWeight = FontWeight.Bold
                    )
                )
            }
            Text(
                modifier = Modifier.fillMaxWidth(0.75f),
                text = buildAnnotatedString {
                    game?.genres?.forEach { genre ->
                        withStyle(
                            style = MaterialTheme.typography.bodyMedium.copy(
                                color = TextSecondary
                            ).toSpanStyle()
                        ) {
                            append(genre.name)
                            if (game.genres.last() != genre) append(" • ")
                        }
                    }
                },
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }

        DescriptionSection(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(bottom = 6.dp),
            description = game?.description ?: ""
        )

        ScreenshotsPager(
            modifier = Modifier
                .fillMaxWidth()
                .windowInsetsPadding(WindowInsets.navigationBars),
            screenshots = game?.screenshots ?: emptyList(),
            pagerState = screenshotsPagerState
        )
    }
}


@Preview(showSystemUi = true)
@Composable
private fun PreviewDetailsPage() {
    DetailsPage(
        game = GameDetails(
            name = "",
            coverUrl = "",
            releaseDate = "",
            rating = 3.5,
            topRating = 4,
            description = "",
            genres = emptyList(),
            screenshots = emptyList()
        )
    )
}