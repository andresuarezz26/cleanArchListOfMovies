package com.movies.cleanarchlistofmovies.domain.usecase

import com.movies.cleanarchlistofmovies.domain.ResultsMovieTVShow
import com.movies.cleanarchlistofmovies.domain.repositories.DiscoverMoviesRepo
import io.reactivex.Single
import javax.inject.Inject

class DiscoverMovies @Inject constructor(private val repo: DiscoverMoviesRepo) {
    operator fun invoke(): Single<List<ResultsMovieTVShow>> = repo()
}