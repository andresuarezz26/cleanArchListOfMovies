package com.movies.cleanarchlistofmovies.data.repositories

import com.movies.cleanarchlistofmovies.data.datasource.ResultTVMovieDataSourceFacade
import com.movies.cleanarchlistofmovies.domain.ResultTVMovies
import com.movies.cleanarchlistofmovies.domain.repositories.GetShowByIdRepoFacade
import io.reactivex.Single
import javax.inject.Inject

class GetShowByIdRepo @Inject constructor(private val dataSource: ResultTVMovieDataSourceFacade) : GetShowByIdRepoFacade {
    override fun invoke(id: Int): Single<ResultTVMovies?> {
        return Single.fromCallable {
            dataSource.getById(id)
        }
    }

}