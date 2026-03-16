package com.malky.bostatask.presentation.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
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
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.malky.bostatask.R
import com.malky.bostatask.ui.theme.AccentYellow
import com.malky.bostatask.ui.theme.Background
import com.malky.bostatask.ui.theme.TextPrimary
import com.malky.bostatask.ui.theme.TextSecondary
import kotlinx.coroutines.launch

@Composable
fun DetailsPage() {
    val englishText =
        """Rockstar Games went bigger, since their previous installment of the series. You get the complicated and realistic world-building from Liberty City of GTA4 in the setting of lively and diverse Los Santos, from an old fan favorite GTA San Andreas. 561 different vehicles (including every transport you can operate) and the amount is rising with every update. \nSimultaneous storytelling from three unique perspectives: \nFollow Michael, ex-criminal living his life of leisure away from the past, Franklin, a kid that seeks the better future, and Trevor, the exact past Michael is trying to run away from. \nGTA Online will provide a lot of additional challenge even for the experienced players, coming fresh from the story mode. Now you will have other players around that can help you just as likely as ruin your mission. Every GTA mechanic up to date can be experienced by players through the unique customizable character, and community content paired with the leveling system tends to keep everyone busy and engaged.\n\nEspañol\nRockstar Games se hizo más grande desde su entrega anterior de la serie. Obtienes la construcción del mundo complicada y realista de Liberty City de GTA4 en el escenario de Los Santos, un viejo favorito de los fans, GTA San Andreas. 561 vehículos diferentes (incluidos todos los transportes que puede operar) y la cantidad aumenta con cada actualización.\nNarración simultánea desde tres perspectivas únicas:\nSigue a Michael, ex-criminal que vive su vida de ocio lejos del pasado, Franklin, un niño que busca un futuro mejor, y Trevor, el pasado exacto del que Michael está tratando de huir.\nGTA Online proporcionará muchos desafíos adicionales incluso para los jugadores experimentados, recién llegados del modo historia. Ahora tendrás otros jugadores cerca que pueden ayudarte con la misma probabilidad que arruinar tu misión. Los jugadores pueden experimentar todas las mecánicas de GTA actualizadas a través del personaje personalizable único, y el contenido de la comunidad combinado con el sistema de nivelación tiende a mantener a todos ocupados y comprometidos.""".trimIndent()


    val pages = remember(englishText) { mutableStateListOf(englishText) }
    val pagerState = rememberPagerState { pages.size }
    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Background),
    ) {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.25f),
            painter = painterResource(R.drawable.gow_cover),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
        Text(
            modifier = Modifier.padding(horizontal = 16.dp),
            text = "God of War Ragnarok",
            style = MaterialTheme.typography.headlineLarge.copy(
                color = TextPrimary,
                fontWeight = FontWeight.Bold
            )
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
                    text = "4.5",
                    style = MaterialTheme.typography.titleMedium.copy(
                        color = TextPrimary,
                        fontWeight = FontWeight.Bold
                    )
                )
            }
            Text(
                text = "Action • Adventure • RPG",
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = TextSecondary
                )
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "About",
                    style = MaterialTheme.typography.headlineSmall.copy(
                        fontWeight = FontWeight.SemiBold,
                        color = TextPrimary
                    )
                )

                Row {
                    IconButton(
                        onClick = {
                            if (pagerState.currentPage > 0) {
                                scope.launch {
                                    pagerState.animateScrollToPage(pagerState.currentPage - 1)
                                }
                            }
                        },
                        enabled = pagerState.currentPage > 0
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                            contentDescription = "Previous",
                            tint = if (pagerState.currentPage > 0) TextPrimary else TextSecondary.copy(
                                alpha = 0.3f
                            )
                        )
                    }
                    IconButton(
                        onClick = {
                            if (pagerState.currentPage < pages.size - 1) {
                                scope.launch {
                                    pagerState.animateScrollToPage(pagerState.currentPage + 1)
                                }
                            }
                        },
                        enabled = pagerState.currentPage < pages.size - 1
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                            contentDescription = "Next",
                            tint = if (pagerState.currentPage < pages.size - 1) TextPrimary else TextSecondary.copy(
                                alpha = 0.3f
                            )
                        )
                    }
                }
            }

            HorizontalPager(
                modifier = Modifier.fillMaxWidth(),
                state = pagerState,
                verticalAlignment = Alignment.Top,
                userScrollEnabled = false
            ) { page ->
                BoxWithConstraints(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 18.dp)
                ) {
                    val density = LocalDensity.current
                    val lineHeight = 20.sp
                    val lineHeightPx = with(density) { lineHeight.toPx() }
                    val maxLines = (constraints.maxHeight / lineHeightPx).toInt().coerceAtLeast(1)

                    Text(
                        text = pages[page],
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontWeight = FontWeight.Medium,
                            color = TextSecondary,
                            lineHeight = lineHeight
                        ),
                        maxLines = maxLines,
                        overflow = TextOverflow.Clip,
                        onTextLayout = { layoutResult ->
                            // If this page overflows and we haven't created the next page yet
                            if (layoutResult.hasVisualOverflow && page == pages.size - 1) {
                                val lastCharIndex = layoutResult.getLineEnd(maxLines - 1)

                                // Avoid cutting words in half
                                val textToSplit = pages[page]
                                var splitIndex = lastCharIndex
                                val lastSpace =
                                    textToSplit.substring(0, lastCharIndex).lastIndexOf(' ')
                                if (lastSpace > lastCharIndex * 0.8) {
                                    splitIndex = lastSpace
                                }

                                val currentPageContent = textToSplit.substring(0, splitIndex).trim()
                                val remainingContent = textToSplit.substring(splitIndex).trim()

                                if (remainingContent.isNotEmpty()) {
                                    pages[page] = currentPageContent
                                    pages.add(remainingContent)
                                }
                            }
                        }
                    )
                }
            }
        }

        ScreenshotsPager(
            modifier = Modifier
                .fillMaxWidth()
                .windowInsetsPadding(WindowInsets.navigationBars)
        )
    }
}


@Preview(showSystemUi = true)
@Composable
private fun PreviewDetailsPage() {
    DetailsPage()
}