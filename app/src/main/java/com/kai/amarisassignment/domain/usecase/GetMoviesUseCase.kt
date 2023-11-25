package com.kai.amarisassignment.domain.usecase

import com.kai.amarisassignment.domain.MovieRepository
import com.kai.amarisassignment.domain.model.ListMovieResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetMoviesUseCase @Inject constructor(
    private val repository: MovieRepository
) {

    suspend fun execute(keyword: String, page: Int): Flow<ListMovieResponse> = flow {

        emit(repository.getMovies(keyword, page))
    }
}