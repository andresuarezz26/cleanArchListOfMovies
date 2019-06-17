package com.movies.cleanarchlistofmovies.data.service

import com.movies.cleanarchlistofmovies.data.api.DiscoverApi
import com.movies.cleanarchlistofmovies.data.mapper.ResultMovieTVShowResponseMapper
import com.movies.cleanarchlistofmovies.domain.ResultsMovieTVShow
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import javax.inject.Inject

interface DiscoverMoviesService {
    operator fun invoke(): Single<List<ResultsMovieTVShow>>
}

class DiscoverMoviesServiceImpl @Inject constructor(
        private val retrofit: Retrofit,
        private val mapper: ResultMovieTVShowResponseMapper
) : DiscoverMoviesService {
    override fun invoke() = retrofit.create(DiscoverApi::class.java)
            .get(
                    "tv",
                    "832bfab4dac5d2d785c0422f3d3f0129",
                    "popularity.desc"
            ).map { response -> mapper.transform(response.results) }
            .subscribeOn(Schedulers.io())
}