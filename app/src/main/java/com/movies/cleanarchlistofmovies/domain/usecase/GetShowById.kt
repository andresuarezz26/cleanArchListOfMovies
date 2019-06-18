package com.movies.cleanarchlistofmovies.domain.usecase

import com.movies.cleanarchlistofmovies.domain.ResultTVMovies
import io.reactivex.Single
import javax.inject.Inject

class GetShowById @Inject constructor() {

    operator fun invoke(id: Int): Single<ResultTVMovies?> {
        return Single.just(ResultTVMovies(1, 1, false, 1.0, "hola", "holj", "fdsaj"))
    }
}