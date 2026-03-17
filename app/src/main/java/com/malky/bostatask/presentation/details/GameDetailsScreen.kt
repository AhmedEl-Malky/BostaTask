package com.malky.bostatask.presentation.details

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.rememberNavController
import com.malky.bostatask.navigations.LocalNavController
import com.malky.bostatask.presentation.composables.DetailsPage
import com.malky.bostatask.presentation.composables.HeroPage
import com.malky.bostatask.ui.theme.Background
import com.malky.bostatask.ui.theme.TextPrimary
import kotlinx.coroutines.launch

@Composable
fun GameDetailsScreen(
    viewModel: GameDetailsViewModel
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    GameDetailsContent(
        state = state
    )
}


@Composable
private fun GameDetailsContent(
    state: GameDetailsState,
    modifier: Modifier = Modifier.fillMaxSize()
) {
    //region internal states
    val pagerState = rememberPagerState { 2 }
    val coroutineScope = rememberCoroutineScope()
    val navController = LocalNavController.current
    //endregion

    Scaffold(
        containerColor = Background,
    ) { innerPadding ->
        Column(
            modifier = modifier
                .background(Background),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Crossfade(targetState = state.isLoading) { isLoading ->
                if (isLoading) {
                    CircularProgressIndicator(
                        color = TextPrimary
                    )
                } else {
                    VerticalPager(
                        state = pagerState,
                        modifier = modifier
                    ) { page ->
                        when (page) {
                            0 -> HeroPage(
                                name = state.gameDetails?.name ?: "",
                                releaseDate = state.gameDetails?.releaseDate,
                                coverUrl = state.gameDetails?.coverUrl,
                                onScrollDown = {
                                    coroutineScope.launch {
                                        pagerState.animateScrollToPage(page + 1)
                                    }
                                },
                                onBack = { navController.popBackStack() }
                            )

                            1 -> DetailsPage(
                                game = state.gameDetails
                            )
                        }
                    }
                }
            }
        }
    }
}


@Preview(showSystemUi = true)
@Composable
private fun PreviewGameDetailsContent() {
    CompositionLocalProvider(
        LocalNavController provides rememberNavController()
    ) {
        GameDetailsContent(
            state = GameDetailsState()
        )
    }
}
