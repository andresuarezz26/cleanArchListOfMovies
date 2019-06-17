package com.movies.cleanarchlistofmovies.domain.usecase

import com.movies.cleanarchlistofmovies.domain.ResultsMovieTVShow
import io.reactivex.Single
import javax.inject.Inject

class DiscoverTVShowAndMovies @Inject constructor() {

    operator fun invoke(): Single<ResultsMovieTVShow> {
        return Single.fromCallable(null)
    }

    data class Param(val showCategory: String)
}