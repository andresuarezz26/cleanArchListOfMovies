package com.movies.cleanarchlistofmovies.data.repositories

import com.movies.cleanarchlistofmovies.data.service.DiscoverMoviesService
import com.movies.cleanarchlistofmovies.domain.ResultsMovieTVShow
import com.movies.cleanarchlistofmovies.domain.repositories.DiscoverMoviesRepo
import io.reactivex.Single
import javax.inject.Inject

class DiscoverMoviesRepoImpl @Inject constructor(
        private val service: DiscoverMoviesService
) : DiscoverMoviesRepo {
    override fun invoke(): Single<List<ResultsMovieTVShow>> = service()
}