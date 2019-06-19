package com.movies.cleanarchlistofmovies.domain.repositories

import com.movies.cleanarchlistofmovies.domain.ResultTVMovies
import io.reactivex.Single

interface DiscoverMoviesTVRepoFacade {
    operator fun invoke(category: String): Single<List<ResultTVMovies>>
}

interface DiscoverMoviesRepoFacade {
    operator fun invoke(category: String): Single<List<ResultTVMovies>>
}

interface DiscoverTVRepoFacade {
    operator fun invoke(category: String): Single<List<ResultTVMovies>>
}