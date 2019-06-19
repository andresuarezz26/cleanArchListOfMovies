package com.movies.cleanarchlistofmovies.domain.repositories

import com.movies.cleanarchlistofmovies.domain.ResultTVMovies
import io.reactivex.Single

interface GetShowByIdRepoFacade {
    operator fun invoke(id: Int): Single<ResultTVMovies?>
}