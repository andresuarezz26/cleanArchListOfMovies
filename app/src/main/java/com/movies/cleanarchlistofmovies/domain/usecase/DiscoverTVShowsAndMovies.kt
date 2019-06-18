package com.movies.cleanarchlistofmovies.domain.usecase

import com.movies.cleanarchlistofmovies.domain.ResultTVMovies
import io.reactivex.Single
import javax.inject.Inject

class DiscoverTVShowAndMovies @Inject constructor(private val discoverMovies: DiscoverMovies) {

    operator fun invoke(param: Param): Single<List<ResultTVMovies>> {
        return discoverMovies()
    }

    data class Param(val showCategory: String)
}