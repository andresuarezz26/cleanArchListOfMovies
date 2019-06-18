package com.movies.cleanarchlistofmovies.domain.usecase

import com.movies.cleanarchlistofmovies.domain.ResultTVMovies
import com.movies.cleanarchlistofmovies.domain.repositories.DiscoverMoviesRepoFacade
import io.reactivex.Single
import javax.inject.Inject

class DiscoverMovies @Inject constructor(private val repo: DiscoverMoviesRepoFacade) {
    operator fun invoke(): Single<List<ResultTVMovies>>
            = repo()
}