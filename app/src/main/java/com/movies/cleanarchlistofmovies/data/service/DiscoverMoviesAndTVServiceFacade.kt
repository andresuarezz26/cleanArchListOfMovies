package com.movies.cleanarchlistofmovies.data.service

import com.movies.cleanarchlistofmovies.data.api.DiscoverApi
import com.movies.cleanarchlistofmovies.data.mapper.ResultMovieTVShowResponseMapper
import com.movies.cleanarchlistofmovies.domain.ResultTVMovies
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import javax.inject.Inject

interface DiscoverMoviesAndTVServiceFacade {
    operator fun invoke(typeOfShow: String, category: String): Single<List<ResultTVMovies>>

    companion object {
        const val TYPE_OF_SHOW_TV = "tv"
        const val TYPE_OF_SHOW_MOVIE = "movie"
    }
}

class DiscoverMoviesService @Inject constructor(
        private val retrofit: Retrofit,
        private val mapper: ResultMovieTVShowResponseMapper
) : DiscoverMoviesAndTVServiceFacade {
    override fun invoke(typeOfShow: String, category: String) = retrofit.create(DiscoverApi::class.java)
            .get(
                    typeOfShow,
                    category
            ).map { response -> mapper.transform(response.results) }
            .subscribeOn(Schedulers.io())
}