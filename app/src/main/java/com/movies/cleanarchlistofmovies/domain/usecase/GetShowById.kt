package com.movies.cleanarchlistofmovies.domain.usecase

import com.movies.cleanarchlistofmovies.domain.ResultTVMovies
import com.movies.cleanarchlistofmovies.domain.repositories.GetShowByIdRepoFacade
import io.reactivex.Single
import javax.inject.Inject

class GetShowById @Inject constructor(val repo: GetShowByIdRepoFacade) {

    operator fun invoke(id: Int): Single<ResultTVMovies?> {
        return repo(id)
    }
}