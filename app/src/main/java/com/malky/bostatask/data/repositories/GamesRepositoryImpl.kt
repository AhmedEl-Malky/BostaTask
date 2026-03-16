package com.malky.bostatask.data.repositories

import com.malky.bostatask.data.mappers.toGame
import com.malky.bostatask.data.mappers.toGameDescription
import com.malky.bostatask.data.remote.GamesService
import com.malky.bostatask.domain.Game
import com.malky.bostatask.utils.DataErrors
import com.malky.bostatask.utils.Result
import com.malky.bostatask.utils.map

class GamesRepositoryImpl(
    private val service: GamesService
) : GamesRepository {
    override suspend fun fetchRemoteGames(): Result<List<Game>, DataErrors.Remote> {
        return service.fetchGames().map { dto ->
            dto.games.map { gameDto ->
                gameDto.toGame()
            }
        }
    }

    override suspend fun fetchGameDescription(id: Int): Result<String, DataErrors.Remote> {
        return service.fetchGameDescription(id = id).map { dto ->
            dto.toGameDescription()
        }
    }
}