package com.malky.bostatask.data.local

import androidx.paging.PagingSource
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
    fun getPagingGamesWithDetails(): PagingSource<Int, GameWithDetails>

    @Transaction
    @Query("SELECT * FROM GameEntity WHERE id = :id")
    fun getGameById(id: Int): Flow<GameWithDetails>


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


    @Query("UPDATE GameEntity SET description = :description WHERE id = :id")
    suspend fun updateGameDescription(id: Int, description: String)

    @Transaction
    suspend fun clearAndUpsertAllGames(
        games: List<GameEntity>,
        genres: List<GenreEntity>,
        screenshots: List<ScreenshotEntity>
    ) {
        clearAllGames()
        upsertAllGamesWithDetails(games, genres, screenshots)
    }

    @Query("DELETE FROM GameEntity")
    suspend fun clearAllGames()
}
