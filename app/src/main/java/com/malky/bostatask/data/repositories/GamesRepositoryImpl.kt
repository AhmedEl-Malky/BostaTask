package com.malky.bostatask.data.repositories

import com.malky.bostatask.data.dto.GamesResponseDTO
import com.malky.bostatask.data.local.GamesDao
import com.malky.bostatask.data.local.entities.GameEntity
import com.malky.bostatask.data.local.entities.GameWithDetails
import com.malky.bostatask.data.local.entities.GenreEntity
import com.malky.bostatask.data.local.entities.ScreenshotEntity
import com.malky.bostatask.data.mappers.toGameDescription
import com.malky.bostatask.data.remote.GamesService
import com.malky.bostatask.data.utils.query
import com.malky.bostatask.utils.DataErrors
import com.malky.bostatask.utils.Result
import com.malky.bostatask.utils.map
import com.malky.bostatask.utils.onSuccess
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach

class GamesRepositoryImpl(
    private val service: GamesService,
    private val dao: GamesDao
) : GamesRepository {

    //region remote data

    override suspend fun fetchGameResponseDto(
        page: Int,
        pageSize: Int
    ): Result<GamesResponseDTO, DataErrors.Remote> {
        return service.fetchGames(
            page = page,
            pageSize = pageSize
        )
    }

    override suspend fun fetchGameDescription(id: Int): Result<String, DataErrors.Remote> {
        return service.fetchGameDescription(id = id).map { dto ->
            dto.toGameDescription()
        }
    }
    //endregion


    //region Local data
    override suspend fun cacheGames(
        games: List<GameEntity>,
        genres: List<GenreEntity>,
        screenshots: List<ScreenshotEntity>
    ): Result<Unit, DataErrors.Local> {
        return query {
            dao.cacheGames(
                games = games,
                genres = genres,
                screenshots = screenshots
            )
        }
    }

    override suspend fun clearCachedGames(
        games: List<GameEntity>,
        genres: List<GenreEntity>,
        screenshots: List<ScreenshotEntity>
    ): Result<Unit, DataErrors.Local> {
        return query {
            dao.clearCachedGames(
                games = games,
                genres = genres,
                screenshots = screenshots
            )
        }
    }

    private suspend fun updateGameDescription(
        id: Int,
        description: String
    ): Result<Unit, DataErrors.Local> {
        return query {
            dao.updateGameDescription(id = id, description = description)
        }
    }

    override fun getGameById(id: Int): Flow<GameWithDetails> {
        return dao.getGameById(id = id).onEach { gameWithDetails ->
            val game = gameWithDetails.game
            if (game.description == null) {
                fetchGameDescription(id = game.id)
                    .onSuccess { description ->
                        updateGameDescription(id = game.id, description = description)
                    }
            }
        }
    }

    override suspend fun getPaginatedGames(limit: Int, offset: Int): List<GameWithDetails> {
        return dao.getPaginatedGames(limit, offset)
    }
    //endregion
}