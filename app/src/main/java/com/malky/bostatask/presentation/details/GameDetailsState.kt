package com.malky.bostatask.presentation.details

import androidx.compose.runtime.Immutable
import com.malky.bostatask.domain.GameDetails

@Immutable
data class GameDetailsState(
    val gameDetails: GameDetails? = null,
    val isLoading: Boolean = true,
    val error: String? = null
)