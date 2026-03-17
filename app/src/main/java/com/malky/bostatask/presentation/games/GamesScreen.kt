package com.malky.bostatask.presentation.games

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.malky.bostatask.R
import com.malky.bostatask.navigations.Destinations
import com.malky.bostatask.navigations.LocalNavController
import com.malky.bostatask.presentation.composables.GameCard
import com.malky.bostatask.presentation.composables.GameCardShimmer
import com.malky.bostatask.presentation.composables.GamesFilters
import com.malky.bostatask.presentation.composables.SearchBar
import com.malky.bostatask.ui.theme.Background
import com.malky.bostatask.ui.theme.TextPrimary
import com.malky.bostatask.ui.theme.TextSecondary

@Composable
fun GamesScreen(
    viewModel: GamesViewModel
) {
    val gamesPager = viewModel.gamesPaging.collectAsLazyPagingItems()
    val state by viewModel.state.collectAsStateWithLifecycle()

    GamesScreenContent(
        gamesPager = gamesPager,
        state = state,
        interactionListener = viewModel
    )
}


@Composable
fun GamesScreenContent(
    modifier: Modifier = Modifier,
    gamesPager: LazyPagingItems<GameUi>,
    state: GamesState,
    interactionListener: GamesInteractionListener
) {
    //region internal states
    val paddingModifier = remember { Modifier.padding(horizontal = 16.dp) }
    val navController = LocalNavController.current
    val snackBarHostState = remember { SnackbarHostState() }
    //endregion

    LaunchedEffect(key1 = gamesPager.loadState.append) {
        if (gamesPager.loadState.append is LoadState.Error) {
            snackBarHostState.showSnackbar(
                message = (gamesPager.loadState.append as LoadState.Error).error.message
                    ?: "Error had been occurred"
            )
        }
    }

    Scaffold(
        containerColor = Background,
        contentWindowInsets = WindowInsets.systemBars,
        snackbarHost = { SnackbarHost(hostState = snackBarHostState) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = paddingModifier
                    .padding(top = 16.dp)
                    .fillMaxWidth(),
                text = "Browse All Games",
                style = MaterialTheme.typography.headlineLarge.copy(
                    fontWeight = FontWeight.Bold,
                    color = TextPrimary
                )
            )
            SearchBar(
                value = state.searchQuery,
                onValueChange = interactionListener::onSearchQueryChanged,
                modifier = paddingModifier.padding(vertical = 8.dp),
            )
            GamesFilters(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                filters = state.genreFilters,
                selectedGenreId = state.selectedGenreId,
                onFilterSelected = interactionListener::onFilterSelected
            )


            if (gamesPager.itemCount == 0 && gamesPager.loadState.refresh is LoadState.Loading) {
                LazyVerticalGrid(
                    modifier = modifier
                        .weight(1f)
                        .background(Background),
                    columns = GridCells.Adaptive(180.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                ) {
                    items(10) {
                        GameCardShimmer(
                            modifier = Modifier.padding(horizontal = 11.dp)
                        )
                    }
                }
            } else if (gamesPager.itemCount == 0 && gamesPager.loadState.refresh is LoadState.Error) {
                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        modifier = Modifier
                            .size(200.dp),
                        painter = painterResource(R.drawable.ghost_gamer),
                        contentDescription = null
                    )
                    Text(
                        modifier = Modifier.padding(vertical = 8.dp),
                        text = (gamesPager.loadState.refresh as LoadState.Error).error.message.toString(),
                        style = MaterialTheme.typography.titleMedium.copy(
                            color = TextSecondary,
                            textAlign = TextAlign.Center
                        ),
                    )
                    Button(
                        onClick = { gamesPager.retry() },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = TextPrimary,
                            contentColor = Background
                        )
                    ) {
                        Text("Retry")
                    }
                }
            } else if (gamesPager.itemCount == 0 && gamesPager.loadState.refresh is LoadState.NotLoading) {
                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        modifier = Modifier
                            .size(200.dp),
                        painter = painterResource(R.drawable.ghost_gamer),
                        contentDescription = null
                    )
                    Text(
                        text = "No games found for \"${state.searchQuery}\"",
                        color = TextSecondary,
                        style = MaterialTheme.typography.titleMedium,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(horizontal = 32.dp)
                    )
                }
            } else {
                LazyVerticalGrid(
                    modifier = modifier
                        .weight(1f)
                        .background(Background),
                    columns = GridCells.Adaptive(180.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                ) {
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
                    items(
                        count = gamesPager.itemCount,
                        key = { index ->
                            val game = gamesPager[index]
                            "${game?.id ?: index}_$index"
                        }
                    ) { index ->
                        gamesPager[index]?.let { game ->
                            GameCard(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(
                                        horizontal = 11.dp
                                    )
                                    .animateItem(),
                                onClick = {
                                    navController.navigate(Destinations.GameDetails(id = game.id))
                                },
                                game = game
                            )
                        }
                    }
                    when (gamesPager.loadState.append) {
                        is LoadState.Loading -> {
                            item(span = { GridItemSpan(maxLineSpan) }) {
                                Column(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    CircularProgressIndicator(
                                        modifier = Modifier.padding(vertical = 16.dp),
                                        color = TextPrimary,
                                        strokeWidth = 4.dp
                                    )
                                }
                            }
                        }

                        is LoadState.Error -> {
                            item(span = { GridItemSpan(maxLineSpan) }) {
                                Button(
                                    onClick = { gamesPager.retry() },
                                    modifier = Modifier
                                        .padding(16.dp)
                                        .fillMaxWidth(),
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = TextPrimary,
                                        contentColor = Background
                                    )
                                ) {
                                    Text("Retry")
                                }
                            }
                        }

                        else -> Unit
                    }
                }
            }
        }
    }
}
