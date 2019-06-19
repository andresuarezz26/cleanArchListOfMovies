package com.movies.cleanarchlistofmovies.domain.repositories

import com.movies.cleanarchlistofmovies.domain.ResultTVMovies
import io.reactivex.Single

interface DiscoverMoviesRepoFacade {
    operator fun invoke(category: String): Single<List<ResultTVMovies>>
}