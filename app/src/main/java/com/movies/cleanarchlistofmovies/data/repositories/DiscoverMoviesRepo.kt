package com.movies.cleanarchlistofmovies.data.repositories

import com.movies.cleanarchlistofmovies.data.datasource.ResultTVMovieDataSourceFacade
import com.movies.cleanarchlistofmovies.data.service.DiscoverMoviesAndTVServiceFacade
import com.movies.cleanarchlistofmovies.data.service.DiscoverMoviesAndTVServiceFacade.Companion.TYPE_OF_SHOW_MOVIE
import com.movies.cleanarchlistofmovies.data.service.DiscoverMoviesAndTVServiceFacade.Companion.TYPE_OF_SHOW_TV
import com.movies.cleanarchlistofmovies.domain.ResultTVMovies
import com.movies.cleanarchlistofmovies.domain.repositories.DiscoverMoviesRepoFacade
import io.reactivex.Flowable
import io.reactivex.Single
import javax.inject.Inject

class DiscoverMoviesRepo @Inject constructor(
        private val discoverService: DiscoverMoviesAndTVServiceFacade,
        private val dataSource: ResultTVMovieDataSourceFacade
) : DiscoverMoviesRepoFacade {
    override fun invoke(category: String): Flowable<List<ResultTVMovies>> {
        return Single.concat(
                discoverService(TYPE_OF_SHOW_TV, category),
                discoverService(TYPE_OF_SHOW_MOVIE, category))
                .doOnNext { dataSource.createOrUpdate(it) }
                .doOnComplete { dataSource.getAllSortedByCategory(category) }
    }
}