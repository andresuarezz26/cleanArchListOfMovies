package com.movies.cleanarchlistofmovies.data.repositories

import com.movies.cleanarchlistofmovies.data.datasource.ResultTVMovieDataSourceFacade
import com.movies.cleanarchlistofmovies.data.service.DiscoverMoviesServiceFacade
import com.movies.cleanarchlistofmovies.domain.ResultTVMovies
import com.movies.cleanarchlistofmovies.domain.repositories.DiscoverMoviesRepoFacade
import com.movies.cleanarchlistofmovies.domain.usecase.DiscoverTVShowAndMovies
import io.reactivex.Single
import javax.inject.Inject

class DiscoverMoviesRepo @Inject constructor(
        private val service: DiscoverMoviesServiceFacade,
        private val dataSource: ResultTVMovieDataSourceFacade
) : DiscoverMoviesRepoFacade {
    override fun invoke(): Single<List<ResultTVMovies>> {
        return service()
                .doOnSuccess { dataSource.createOrUpdate(it) }
                .onErrorResumeNext { Single.just(dataSource.getAll()) }
    }
}