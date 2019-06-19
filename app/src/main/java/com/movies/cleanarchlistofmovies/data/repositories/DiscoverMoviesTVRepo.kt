package com.movies.cleanarchlistofmovies.data.repositories

import com.movies.cleanarchlistofmovies.data.Constants
import com.movies.cleanarchlistofmovies.domain.ResultTVMovies
import com.movies.cleanarchlistofmovies.domain.repositories.DiscoverMoviesTVRepoFacade
import io.reactivex.Single
import io.reactivex.functions.BiFunction
import javax.inject.Inject

class DiscoverMoviesTVRepo @Inject constructor(
        private val movieRepo: DiscoverMoviesRepo,
        private val tvRepo: DiscoverTVRepo
) : DiscoverMoviesTVRepoFacade {
    override fun invoke(category: String): Single<List<ResultTVMovies>> {
        return Single
                .zip(movieRepo(category), tvRepo(category),
                        BiFunction<List<ResultTVMovies>,
                                List<ResultTVMovies>, List<ResultTVMovies>> { tvList, movieList ->
                            mergeAndSortMoviesAndTVShows(tvList, movieList, category)
                        })
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