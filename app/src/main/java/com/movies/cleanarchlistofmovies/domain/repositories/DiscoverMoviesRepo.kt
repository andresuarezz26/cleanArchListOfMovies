package com.movies.cleanarchlistofmovies.domain.repositories

import com.movies.cleanarchlistofmovies.domain.ResultsMovieTVShow
import io.reactivex.Single

interface DiscoverMoviesRepo {
    operator fun invoke(): Single<List<ResultsMovieTVShow>>
}