package com.malky.bostatask.presentation.games

import com.malky.bostatask.domain.Game

data class GamesState(
    val games: List<Game> = emptyList(),
    val isLoading: Boolean = true,
    val isPagingLoading: Boolean = false,
    val error: String? = null,
)
