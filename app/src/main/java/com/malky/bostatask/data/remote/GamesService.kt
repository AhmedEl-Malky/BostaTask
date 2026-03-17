package com.malky.bostatask.data.remote

import com.malky.bostatask.data.dto.GameDescriptionDTO
import com.malky.bostatask.data.dto.GamesResponseDTO
import com.malky.bostatask.utils.DataErrors
import com.malky.bostatask.utils.Result

interface GamesService {
    suspend fun fetchGames(
        page: Int,
        pageSize: Int
    ): Result<GamesResponseDTO, DataErrors.Remote>
    suspend fun fetchGameDescription(id: Int): Result<GameDescriptionDTO, DataErrors.Remote>
}