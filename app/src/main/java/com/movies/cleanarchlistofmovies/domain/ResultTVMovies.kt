package com.movies.cleanarchlistofmovies.domain

data class ResultTVMovies(
        val voteCount: Int,
        val id: Int,
        val video: Boolean,
        val voteAverage: Double,
        val title: String?,
        val posterPath: String?,
        val overview: String?
)