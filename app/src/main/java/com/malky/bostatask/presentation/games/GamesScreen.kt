package com.malky.bostatask.presentation.games

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.malky.bostatask.navigations.Destinations
import com.malky.bostatask.navigations.LocalNavController
import com.malky.bostatask.presentation.composables.GameCard
import com.malky.bostatask.presentation.composables.GamesFilters
import com.malky.bostatask.ui.theme.Background
import com.malky.bostatask.ui.theme.TextPrimary

@Composable
fun GamesScreen(
    state: GamesState,
) {
    GamesScreenContent(
        state = state
    )
}


@Composable
fun GamesScreenContent(
    state: GamesState,
    modifier: Modifier = Modifier
) {
    //region internal states
    val paddingModifier = remember { Modifier.padding(horizontal = 16.dp) }
    val navController = LocalNavController.current
    //endregion

    Scaffold(
        containerColor = Background,
        contentWindowInsets = WindowInsets.systemBars,
    ) { innerPadding ->
        Crossfade(targetState = state.isLoading) {
            if (it) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(
                        color = TextPrimary
                    )
                }
            } else {
                LazyVerticalGrid(
                    modifier = modifier
                        .padding(innerPadding)
                        .fillMaxSize()
                        .background(Background),
                    columns = GridCells.Adaptive(180.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                ) {
                    item(span = { GridItemSpan(maxLineSpan) }) {
                        Text(
                            modifier = paddingModifier,
                            text = "Browse All Games",
                            style = MaterialTheme.typography.headlineLarge.copy(
                                fontWeight = FontWeight.Bold,
                                color = TextPrimary
                            )
                        )
                    }
                    item(span = { GridItemSpan(maxLineSpan) }) {
                        GamesFilters(
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                    item(span = { GridItemSpan(maxLineSpan) }) {
                        Text(
                            modifier = paddingModifier,
                            text = "Trending Now",
                            style = MaterialTheme.typography.headlineSmall.copy(
                                fontWeight = FontWeight.SemiBold,
                                color = TextPrimary
                            )
                        )
                    }
                    itemsIndexed(state.games) { index, game ->
                        GameCard(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(
                                    horizontal = 11.dp
                                ),
                            onClick = {
                                navController.navigate(Destinations.GameDetails(id = 1))
                            },
                            game = game
                        )
                    }

                }
            }
        }
    }
}

//@PreviewScreenSizes
@Preview(showSystemUi = true)
@Composable
private fun PreviewGamesContent() {
    CompositionLocalProvider(
        LocalNavController provides rememberNavController()
    ) {
        GamesScreenContent(
            state = GamesState(isLoading = true)
        )
    }
}