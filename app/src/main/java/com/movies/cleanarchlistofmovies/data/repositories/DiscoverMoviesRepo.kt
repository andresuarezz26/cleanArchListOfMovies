package com.movies.cleanarchlistofmovies.data.repositories

import com.movies.cleanarchlistofmovies.data.Constants
import com.movies.cleanarchlistofmovies.data.datasource.ResultTVMovieDataSourceFacade
import com.movies.cleanarchlistofmovies.data.service.DiscoverServiceFacade
import com.movies.cleanarchlistofmovies.data.service.DiscoverServiceFacade.Companion.MOVIE_SHOW
import com.movies.cleanarchlistofmovies.data.service.DiscoverServiceFacade.Companion.TV_SHOW
import com.movies.cleanarchlistofmovies.domain.ResultTVMovies
import com.movies.cleanarchlistofmovies.domain.repositories.DiscoverMoviesRepoFacade
import io.reactivex.Single
import io.reactivex.functions.BiFunction
import javax.inject.Inject

class DiscoverMoviesRepo @Inject constructor(
        private val service: DiscoverServiceFacade,
        private val dataSource: ResultTVMovieDataSourceFacade
) : DiscoverMoviesRepoFacade {
    override fun invoke(category: String): Single<List<ResultTVMovies>> {
        return Single
                .zip(service(TV_SHOW, category), service(MOVIE_SHOW, category),
                        BiFunction<List<ResultTVMovies>,
                                List<ResultTVMovies>, List<ResultTVMovies>> { tvList, movieList ->
                            mergeAndSortMoviesAndTVShows(tvList, movieList, category)
                        })
                .doOnSuccess {
                    dataSource.createOrUpdate(it)
                }
                .onErrorResumeNext { Single.just(dataSource.getAll()) }
    }

    private fun mergeAndSortMoviesAndTVShows(
            list1: List<ResultTVMovies>,
            list2: List<ResultTVMovies>,
            category: String): List<ResultTVMovies> {
        val list = mutableListOf<ResultTVMovies>()
        list.addAll(list1)
        list.addAll(list2)

        when (category) {
            Constants.CATEGORY_TOP_RATED -> list.sortByDescending { selectorTopRated(it) }
            Constants.CATEGORY_POPULAR -> list.sortByDescending { selectorPopular(it) }
            else -> list.sortBy { selectorPopular(it) }
        }

        return list
    }

    private fun selectorPopular(result: ResultTVMovies): Int = result.voteCount
    private fun selectorTopRated(result: ResultTVMovies): Double = result.voteAverage

}