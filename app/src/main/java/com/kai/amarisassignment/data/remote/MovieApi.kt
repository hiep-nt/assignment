package com.kai.amarisassignment.data.remote

import com.kai.amarisassignment.domain.model.ListMovieResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {

    @GET("/?apikey=b9bd48a6&type=movie")
    suspend fun getMovies(
        @Query("s") keyword: String = "",
        @Query("page") page: Int = 1
    ): ListMovieResponse
}