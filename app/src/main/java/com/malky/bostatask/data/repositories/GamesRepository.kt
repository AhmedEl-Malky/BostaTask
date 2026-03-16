package com.malky.bostatask.data.repositories

import com.malky.bostatask.domain.Game
import com.malky.bostatask.utils.DataErrors
import com.malky.bostatask.utils.Result

interface GamesRepository {
    suspend fun fetchRemoteGames(): Result<List<Game>, DataErrors.Remote>

    suspend fun fetchGameDescription(id: Int): Result<String, DataErrors.Remote>

}