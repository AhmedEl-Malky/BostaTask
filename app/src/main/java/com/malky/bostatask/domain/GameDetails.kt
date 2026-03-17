package com.malky.bostatask.domain

data class GameDetails(
    val name: String,
    val coverUrl: String?,
    val releaseDate: String?,
    val rating: Double,
    val topRating: Int?,
    val description: String,
    val genres: List<Genre> = emptyList(),
    val screenshots: List<Screenshot> = emptyList()
)
