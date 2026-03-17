package com.malky.bostatask.data

import androidx.core.net.toUri
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.malky.bostatask.data.local.entities.GameWithDetails
import com.malky.bostatask.data.mappers.toGameEntity
import com.malky.bostatask.data.mappers.toGenreEntity
import com.malky.bostatask.data.mappers.toScreenshotEntity
import com.malky.bostatask.data.repositories.GamesRepository
import com.malky.bostatask.utils.Result

@OptIn(ExperimentalPagingApi::class)
class GamesRemoteMediator(
    private val repo: GamesRepository
) : RemoteMediator<Int, GameWithDetails>() {
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, GameWithDetails>
    ): MediatorResult {
        val page = when (loadType) {
            LoadType.REFRESH -> 1
            LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
            LoadType.APPEND -> {
                state.lastItemOrNull() ?: return MediatorResult.Success(
                    endOfPaginationReached = false
                )
                state.pages.size + 1
            }
        }

        val result = repo.fetchGameResponseDto(
            page = page,
            pageSize = state.config.pageSize
        )

        return when (result) {
            is Result.Error -> MediatorResult.Error(Exception(result.error.toString()))
            is Result.Success -> {
                val gamesResponse = result.data
                val games = gamesResponse.games

                if (loadType == LoadType.REFRESH) {
                    repo.clearAndUpsertAllGames(
                        games = games.map { it.toGameEntity() },
                        genres = games.flatMap { game ->
                            game.genres.map { it.toGenreEntity(game.id) }
                        },
                        screenshots = games.flatMap { game ->
                            game.shortScreenshots.map { it.toScreenshotEntity(game.id) }
                        }
                    )
                } else {
                    repo.upsertAllGamesWithDetails(
                        games = games.map { it.toGameEntity() },
                        genres = games.flatMap { game ->
                            game.genres.map { it.toGenreEntity(game.id) }
                        },
                        screenshots = games.flatMap { game ->
                            game.shortScreenshots.map { it.toScreenshotEntity(game.id) }
                        }
                    )
                }
                MediatorResult.Success(endOfPaginationReached = gamesResponse.next == null)
            }

        }
    }

    private fun extractPageCountFromUrl(nextUrl: String?): Int? {
        return nextUrl?.toUri()?.getQueryParameter("page")?.toIntOrNull()
    }
}