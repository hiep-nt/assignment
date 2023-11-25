package com.kai.amarisassignment.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kai.amarisassignment.domain.model.ListMovieResponse.Movie
import com.kai.amarisassignment.domain.usecase.GetMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getMoviesUseCase: GetMoviesUseCase
) : ViewModel() {

    private val searchKeyword = MutableStateFlow("")

    private val currentPage = MutableStateFlow(1)

    private val totalResults = MutableStateFlow(0)

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    private val _listMoviesFlow = MutableStateFlow<List<Movie>>(emptyList())
    val listMoviesFlow = _listMoviesFlow.asStateFlow()

    init {
        combine(searchKeyword, currentPage) { keyword, page ->
            Pair(keyword, page)
        }.flatMapLatest { (keyword, page) ->
            _isLoading.value = true
            if (page == 1) _listMoviesFlow.value = emptyList()
            val response = getMoviesUseCase.execute(keyword, page)
            totalResults.value = response.first().totalResults.ifEmpty { "0" }.toInt()
            response.map { it.listMovies }
        }.onEach { movies ->
            _listMoviesFlow.value += movies
            _isLoading.value = false
        }.launchIn(viewModelScope)
    }

    fun loadMore() {
        if (_listMoviesFlow.value.size < totalResults.value && !_isLoading.value) {
            currentPage.value++
        }
    }

    fun updateSearchKeyword(data: String) {
        searchKeyword.value = data
        currentPage.value = 1
    }
}
