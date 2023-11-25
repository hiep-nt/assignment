package com.kai.amarisassignment.domain.model

import com.google.gson.annotations.SerializedName

data class ListMovieResponse(
    val response: String = "",
    @SerializedName("Search") val listMovies: List<Movie> = listOf(),
    val totalResults: String = ""
) {
    data class Movie(
        @SerializedName("Poster") val poster: String = "",
        @SerializedName("Title") val title: String = "",
        @SerializedName("Type") val type: String = "",
        @SerializedName("Year") val year: String = "",
        val imdbID: String = ""
    )
}