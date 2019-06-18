package com.movies.cleanarchlistofmovies.domain.usecase

import com.movies.cleanarchlistofmovies.domain.ResultsMovieTVShow
import io.reactivex.Single
import javax.inject.Inject

class DiscoverTVShowAndMovies @Inject constructor(private val discoverMovies: DiscoverMovies) {

    operator fun invoke(): Single<List<ResultsMovieTVShow>> {
        return discoverMovies()
    }

    data class Param(val showCategory: String)
}