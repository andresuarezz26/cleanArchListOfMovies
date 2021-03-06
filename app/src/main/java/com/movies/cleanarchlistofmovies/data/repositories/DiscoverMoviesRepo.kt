package com.movies.cleanarchlistofmovies.data.repositories

import com.movies.cleanarchlistofmovies.data.datasource.ResultTVMovieDataSourceFacade
import com.movies.cleanarchlistofmovies.data.service.DiscoverServiceFacade
import com.movies.cleanarchlistofmovies.domain.ResultTVMovies
import com.movies.cleanarchlistofmovies.domain.repositories.DiscoverMoviesRepoFacade
import io.reactivex.Single
import javax.inject.Inject

class DiscoverMoviesRepo @Inject constructor(
        private val service: DiscoverServiceFacade,
        private val dataSource: ResultTVMovieDataSourceFacade
) : DiscoverMoviesRepoFacade {
    override fun invoke(category: String): Single<List<ResultTVMovies>> {
        return service(DiscoverServiceFacade.MOVIE_SHOW, category)
                .doOnSuccess { dataSource.createOrUpdate(it, category, DiscoverServiceFacade.MOVIE_SHOW) }
                .onErrorResumeNext {
                    Single.just(dataSource.getAllByCategory(
                            DiscoverServiceFacade.MOVIE_SHOW, category))
                }
    }
}