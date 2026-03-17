package com.malky.bostatask.data.local

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import com.malky.bostatask.data.local.entities.GameEntity
import com.malky.bostatask.data.local.entities.GameWithDetails
import com.malky.bostatask.data.local.entities.GenreEntity
import com.malky.bostatask.data.local.entities.ScreenshotEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface GamesDao {
    /**
     * Implements manual pagination using SQL LIMIT and OFFSET.
     * 
     * @param limit The number of items to fetch for a single page.
     * @param offset The starting position from which to begin fetching data.
     * @return A list of [GameWithDetails] objects.
     */
    @Transaction
    @Query("SELECT * FROM GameEntity LIMIT :limit OFFSET :offset")
    suspend fun getPaginatedGames(limit: Int, offset: Int): List<GameWithDetails>


    @Transaction
    suspend fun cacheGames(
        games: List<GameEntity>,
        genres: List<GenreEntity>,
        screenshots: List<ScreenshotEntity>
    ) {
        upsertGames(games)
        upsertGenres(genres)
        upsertScreenshots(screenshots)
    }

    @Upsert
    suspend fun upsertGames(games: List<GameEntity>)

    @Upsert
    suspend fun upsertGenres(genres: List<GenreEntity>)

    @Upsert
    suspend fun upsertScreenshots(screenshots: List<ScreenshotEntity>)

    @Transaction
    suspend fun clearCachedGames(
        games: List<GameEntity>,
        genres: List<GenreEntity>,
        screenshots: List<ScreenshotEntity>
    ) {
        clearAllGames()
        clearAllGenres()
        clearAllScreenshots()
    }

    @Query("DELETE FROM GameEntity")
    suspend fun clearAllGames()

    @Query("DELETE FROM GenreEntity")
    suspend fun clearAllGenres()

    @Query("DELETE FROM ScreenshotEntity")
    suspend fun clearAllScreenshots()

    @Query("UPDATE GameEntity SET description = :description WHERE id = :id")
    suspend fun updateGameDescription(id: Int, description: String)

    @Transaction
    @Query("SELECT * FROM GameEntity WHERE id = :id")
    fun getGameById(id: Int): Flow<GameWithDetails>
}
