package com.kai.amarisassignment.domain

import com.kai.amarisassignment.domain.model.ListMovieResponse

interface MovieRepository {

    suspend fun getMovies(keyword: String, page: Int): ListMovieResponse
}