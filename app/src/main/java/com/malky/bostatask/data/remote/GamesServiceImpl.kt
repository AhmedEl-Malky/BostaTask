package com.malky.bostatask.data.remote

import com.malky.bostatask.data.dto.GameDescriptionDTO
import com.malky.bostatask.data.dto.GamesResponseDTO
import com.malky.bostatask.data.utils.request
import com.malky.bostatask.utils.DataErrors
import com.malky.bostatask.utils.Result

class GamesServiceImpl(
    private val service: RetrofitGamesService,
) : GamesService {
    override suspend fun fetchGames(): Result<GamesResponseDTO, DataErrors.Remote> {
        return request { service.fetchGames() }
    }

    override suspend fun fetchGameDescription(id: Int): Result<GameDescriptionDTO, DataErrors.Remote> {
        return request { service.fetchGameDescription(id = id) }
    }

}