package com.malky.bostatask.presentation.games

import com.malky.bostatask.domain.Genre

data class GamesState(
    val genreFilters: List<Genre> = listOf(
        Genre(id = 0, name = "All"),
        Genre(id = 4, name = "Action"),
        Genre(id = 5, name = "RPG"),
        Genre(id = 2, name = "Shooter"),
        Genre(id = 7, name = "Puzzle"),
        Genre(id = 3, name = "Adventure"),
        Genre(id = 51, name = "Indie"),
        Genre(id = 83, name = "Platformer"),
        Genre(id = 59, name = "Massively Multiplayer"),
        Genre(id = 14, name = "Simulation"),
        Genre(id = 40, name = "Casual"),
        Genre(id = 6, name = "Fighting"),
        Genre(id = 10, name = "Strategy"),
    ),
    val selectedGenreId: Int = 0,
    val searchQuery: String = "",
)
