package com.movies.cleanarchlistofmovies.data.repositories

import com.movies.cleanarchlistofmovies.data.datasource.ResultTVMovieDataSourceFacade
import com.movies.cleanarchlistofmovies.data.service.DiscoverServiceFacade
import com.movies.cleanarchlistofmovies.domain.ResultTVMovies
import com.movies.cleanarchlistofmovies.domain.repositories.DiscoverTVRepoFacade
import io.reactivex.Single
import javax.inject.Inject

class DiscoverTVRepo @Inject constructor(
        private val service: DiscoverServiceFacade,
        private val dataSource: ResultTVMovieDataSourceFacade
) : DiscoverTVRepoFacade {
    override fun invoke(category: String): Single<List<ResultTVMovies>> {
        return service(DiscoverServiceFacade.TV_SHOW, category)
                .doOnSuccess { dataSource.createOrUpdate(it, category, DiscoverServiceFacade.TV_SHOW) }
                .onErrorResumeNext {
                    Single.just(dataSource.getAllByCategory(
                            DiscoverServiceFacade.TV_SHOW, category))
                }
    }
}