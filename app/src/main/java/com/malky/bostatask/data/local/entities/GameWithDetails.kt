package com.malky.bostatask.data.local.entities

import androidx.room.Embedded
import androidx.room.Relation

data class GameWithDetails(
    @Embedded val game: GameEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "gameId"
    )
    val genres: List<GenreEntity>,
    @Relation(
        parentColumn = "id",
        entityColumn = "gameId"
    )
    val screenshots: List<ScreenshotEntity>
)
