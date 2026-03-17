package com.malky.bostatask.data

import androidx.core.net.toUri
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.malky.bostatask.data.mappers.toGame
import com.malky.bostatask.data.mappers.toGameEntity
import com.malky.bostatask.data.mappers.toGenreEntity
import com.malky.bostatask.data.mappers.toScreenshotEntity
import com.malky.bostatask.data.repositories.GamesRepository
import com.malky.bostatask.domain.Game
import com.malky.bostatask.utils.Result

class GamesPagingSource(
    private val repository: GamesRepository
) : PagingSource<Int, Game>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Game> {
        val page = params.key ?: 1
        val pageSize = params.loadSize
        val offset = (page - 1) * pageSize

        val localGames = repository.getPaginatedGames(limit = pageSize, offset = offset)

        return if (localGames.isNotEmpty()) {
            LoadResult.Page(
                data = localGames.map { it.toGame() },
                prevKey = if (page == 1) null else page - 1,
                nextKey = page + 1
            )
        } else {
            val result = repository.fetchGameResponseDto(page = page, pageSize = pageSize)

            when (result) {
                is Result.Error -> LoadResult.Error(Exception(result.error.message))
                is Result.Success -> {
                    val gamesDto = result.data.games

                    repository.cacheGames(
                        games = gamesDto.map { it.toGameEntity() },
                        genres = gamesDto.flatMap { game ->
                            game.genres.map { it.toGenreEntity(game.id) }
                        },
                        screenshots = gamesDto.flatMap { game ->
                            game.shortScreenshots.map { it.toScreenshotEntity(game.id) }
                        }
                    )

                    LoadResult.Page(
                        data = gamesDto.map { it.toGame() },
                        nextKey = extractPageCountFromUrl(result.data.next),
                        prevKey = extractPageCountFromUrl(result.data.previous)
                    )
                }
            }
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Game>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    private fun extractPageCountFromUrl(nextUrl: String?): Int? {
        return nextUrl?.toUri()?.getQueryParameter("page")?.toIntOrNull()
    }
}
