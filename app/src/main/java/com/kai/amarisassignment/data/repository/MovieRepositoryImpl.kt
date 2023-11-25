package com.kai.amarisassignment.data.repository

import com.kai.amarisassignment.data.remote.MovieApi
import com.kai.amarisassignment.domain.MovieRepository
import com.kai.amarisassignment.domain.model.ListMovieResponse
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val movieApi: MovieApi
) : MovieRepository {

    override suspend fun getMovies(keyword: String, page: Int): ListMovieResponse {
        return movieApi.getMovies(keyword, page)
    }
}