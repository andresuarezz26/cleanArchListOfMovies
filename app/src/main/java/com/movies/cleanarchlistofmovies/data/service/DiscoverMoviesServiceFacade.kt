package com.movies.cleanarchlistofmovies.data.service

import com.movies.cleanarchlistofmovies.data.api.DiscoverApi
import com.movies.cleanarchlistofmovies.data.mapper.ResultMovieTVShowResponseMapper
import com.movies.cleanarchlistofmovies.domain.ResultTVMovies
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import javax.inject.Inject

interface DiscoverMoviesServiceFacade {
    operator fun invoke(): Single<List<ResultTVMovies>>
}

class DiscoverMoviesService @Inject constructor(
        private val retrofit: Retrofit,
        private val mapper: ResultMovieTVShowResponseMapper
) : DiscoverMoviesServiceFacade {
    override fun invoke() = retrofit.create(DiscoverApi::class.java)
            .get(
                    "movie",
                    "popularity.asc"
            ).map { response -> mapper.transform(response.results) }
            .subscribeOn(Schedulers.io())
}