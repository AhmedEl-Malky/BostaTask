package com.malky.bostatask.data.mappers

import com.malky.bostatask.data.dto.GameDTO
import com.malky.bostatask.data.dto.GameDescriptionDTO
import com.malky.bostatask.data.dto.GenreDTO
import com.malky.bostatask.data.dto.ScreenshotDTO
import com.malky.bostatask.data.local.entities.GameEntity
import com.malky.bostatask.data.local.entities.GameWithDetails
import com.malky.bostatask.data.local.entities.GenreEntity
import com.malky.bostatask.data.local.entities.ScreenshotEntity
import com.malky.bostatask.domain.Game
import com.malky.bostatask.domain.GameDetails
import com.malky.bostatask.domain.Genre
import com.malky.bostatask.domain.Screenshot
import com.malky.bostatask.presentation.games.GameUi

fun GameDTO.toGame(): Game {
    return Game(
        id = this.id,
        name = this.name,
        rating = this.rating,
        coverUrl = this.backgroundImage,
        genres = this.genres.map { it.toGenre() },
        screenshots = this.shortScreenshots.map { it.toScreenShot() }
    )
}

fun Game.toGameUi(): GameUi {
    return GameUi(
        id = id,
        name = name,
        rating = rating,
        coverUrl = coverUrl,
        genres = genres
    )
}

fun GameDTO.toGameEntity(): GameEntity {
    return GameEntity(
        id = this.id,
        name = this.name,
        rating = this.rating,
        ratingTop = this.ratingTop,
        backgroundImage = this.backgroundImage,
        released = this.released,
        description = null
    )
}

fun GameWithDetails.toGame(): Game {
    return Game(
        id = game.id,
        name = game.name,
        rating = game.rating,
        coverUrl = game.backgroundImage,
        genres = genres.map { it.toGenre() },
        screenshots = screenshots.map { it.toScreenshot() }
    )
}

fun GameWithDetails.toGameDetails(): GameDetails {
    return GameDetails(
        name = this.game.name,
        coverUrl = this.game.backgroundImage,
        releaseDate = this.game.released,
        rating = this.game.rating,
        topRating = this.game.ratingTop,
        description = this.game.description ?: "",
        genres = this.genres.map { it.toGenre() },
        screenshots = this.screenshots.map { it.toScreenshot() }
    )
}

fun GameDescriptionDTO.toGameDescription(): String = this.descriptionRaw

fun ScreenshotDTO.toScreenShot(): Screenshot {
    return Screenshot(
        id = this.id,
        url = this.image
    )
}

fun ScreenshotDTO.toScreenshotEntity(gameId: Int): ScreenshotEntity {
    return ScreenshotEntity(
        id = this.id,
        gameId = gameId,
        image = this.image
    )
}

fun ScreenshotEntity.toScreenshot(): Screenshot {
    return Screenshot(
        id = this.id,
        url = this.image
    )
}

fun GenreDTO.toGenre(): Genre {
    return Genre(
        id = this.id,
        name = this.name
    )
}

fun GenreEntity.toGenre(): Genre {
    return Genre(
        id = this.id,
        name = this.name
    )
}

fun GenreDTO.toGenreEntity(gameId: Int): GenreEntity {
    return GenreEntity(
        id = this.id,
        gameId = gameId,
        name = this.name
    )
}






