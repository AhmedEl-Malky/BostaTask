package com.malky.bostatask.data

import androidx.core.net.toUri
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.malky.bostatask.data.mappers.toGame
import com.malky.bostatask.data.repositories.GamesRepository
import com.malky.bostatask.domain.Game
import com.malky.bostatask.utils.Result
import kotlinx.coroutines.delay

class GamesPagingSource(
    private val repository: GamesRepository
) : PagingSource<Int, Game>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Game> {
        val page = params.key ?: 1
        val pageSize = params.loadSize
        delay(2000)
        val result = repository.fetchGameResponseDto(page = page, pageSize = pageSize)

        return when (result) {
            is Result.Error -> LoadResult.Error(Exception(result.error.message))
            is Result.Success -> {
                LoadResult.Page(
                    data = result.data.games.map { it.toGame() },
                    nextKey = extractPageCountFromUrl(result.data.next),
                    prevKey = extractPageCountFromUrl(result.data.previous)
                )
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