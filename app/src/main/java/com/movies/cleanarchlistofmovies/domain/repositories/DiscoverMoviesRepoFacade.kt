package com.movies.cleanarchlistofmovies.domain.repositories

import com.movies.cleanarchlistofmovies.domain.ResultTVMovies
import com.movies.cleanarchlistofmovies.domain.usecase.DiscoverTVShowAndMovies
import io.reactivex.Single

interface DiscoverMoviesRepoFacade {
    operator fun invoke(): Single<List<ResultTVMovies>>
}