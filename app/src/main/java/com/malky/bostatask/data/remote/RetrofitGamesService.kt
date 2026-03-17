package com.malky.bostatask.data.remote

import com.malky.bostatask.data.dto.GameDescriptionDTO
import com.malky.bostatask.data.dto.GamesResponseDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface RetrofitGamesService {
    @GET("games")
    suspend fun fetchGames(
        @Query("page") page: Int,
        @Query("page_size") pageSize: Int,
    ): Response<GamesResponseDTO>

    @GET("games/{id}")
    suspend fun fetchGameDescription(@Path("id") id: Int): Response<GameDescriptionDTO>

}