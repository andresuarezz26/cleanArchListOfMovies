package com.movies.cleanarchlistofmovies.domain.usecase

import com.movies.cleanarchlistofmovies.domain.ResultTVMovies
import com.movies.cleanarchlistofmovies.domain.repositories.DiscoverMoviesTVRepoFacade
import io.reactivex.Single
import javax.inject.Inject

class DiscoverTVShowAndMoviesByCategory @Inject constructor(private val repo: DiscoverMoviesTVRepoFacade) {

    operator fun invoke(param: Param): Single<List<ResultTVMovies>> {
        return repo(param.showCategory)
    }

    data class Param(val showCategory: String)
}