package com.malky.bostatask.navigations

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import androidx.paging.compose.collectAsLazyPagingItems
import com.malky.bostatask.presentation.details.GameDetailsScreen
import com.malky.bostatask.presentation.games.GamesScreen
import com.malky.bostatask.presentation.games.GamesViewModel

val LocalNavController =
    staticCompositionLocalOf<NavHostController> { error("NavController not found") }

@Composable
fun NavigationGraph() {
    val navController = rememberNavController()
    CompositionLocalProvider(
        LocalNavController provides navController
    ) {
        NavHost(navController = navController, startDestination = Destinations.AppGraph) {
            navigation<Destinations.AppGraph>(startDestination = Destinations.Games) {
                composable<Destinations.Games> {
                    val viewModel: GamesViewModel = hiltViewModel()
                    val gamesPager = viewModel.gamesPagingFlow.collectAsLazyPagingItems()
                    GamesScreen(
                        gamesPager = gamesPager
                    )
                }
                composable<Destinations.GameDetails> {
                    GameDetailsScreen()
                }
            }
        }
    }
}