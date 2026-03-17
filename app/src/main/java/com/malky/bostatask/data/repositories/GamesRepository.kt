package com.malky.bostatask.data.repositories

import com.malky.bostatask.data.dto.GamesResponseDTO
import com.malky.bostatask.data.local.entities.GameEntity
import com.malky.bostatask.data.local.entities.GameWithDetails
import com.malky.bostatask.data.local.entities.GenreEntity
import com.malky.bostatask.data.local.entities.ScreenshotEntity
import com.malky.bostatask.utils.DataErrors
import com.malky.bostatask.utils.Result
import kotlinx.coroutines.flow.Flow

interface GamesRepository {

    suspend fun fetchGameResponseDto(
        page: Int,
        pageSize: Int
    ): Result<GamesResponseDTO, DataErrors.Remote>

    suspend fun fetchGameDescription(id: Int): Result<String, DataErrors.Remote>

    suspend fun cacheGames(
        games: List<GameEntity>,
        genres: List<GenreEntity>,
        screenshots: List<ScreenshotEntity>
    ): Result<Unit, DataErrors.Local>

    suspend fun clearCachedGames(
        games: List<GameEntity>,
        genres: List<GenreEntity>,
        screenshots: List<ScreenshotEntity>
    ): Result<Unit, DataErrors.Local>

    fun getGameById(id: Int): Flow<GameWithDetails>

    suspend fun getPaginatedGames(limit: Int, offset: Int): List<GameWithDetails>
}