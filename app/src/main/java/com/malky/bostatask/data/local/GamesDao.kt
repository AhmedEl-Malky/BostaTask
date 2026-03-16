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
    @Transaction
    @Query("SELECT * FROM GameEntity")
    fun getGamesWithDetails(): Flow<List<GameWithDetails>>

    @Transaction
    @Query("SELECT * FROM GameEntity WHERE id = :id")
    fun getGameById(id: Int): Flow<GameWithDetails?>


    @Upsert
    suspend fun upsertGames(games: List<GameEntity>)

    @Upsert
    suspend fun upsertGenres(genres: List<GenreEntity>)

    @Upsert
    suspend fun upsertScreenshots(screenshots: List<ScreenshotEntity>)

    @Transaction
    suspend fun upsertAllGamesWithDetails(
        games: List<GameEntity>,
        genres: List<GenreEntity>,
        screenshots: List<ScreenshotEntity>
    ) {
        upsertGames(games)
        upsertGenres(genres)
        upsertScreenshots(screenshots)
    }
}