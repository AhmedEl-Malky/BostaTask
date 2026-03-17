package com.malky.bostatask.presentation.games

import com.malky.bostatask.domain.Genre

data class GameUi(
    val id: Int,
    val name: String,
    val rating: Double,
    val coverUrl: String?,
    val genres: List<Genre>,
)
