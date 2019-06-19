package com.movies.cleanarchlistofmovies.data.service

import com.movies.cleanarchlistofmovies.data.api.DiscoverApi
import com.movies.cleanarchlistofmovies.data.mapper.ResultMovieTVShowResponseMapper
import com.movies.cleanarchlistofmovies.domain.ResultTVMovies
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import javax.inject.Inject

interface DiscoverServiceFacade {
    operator fun invoke(typeOfShow: String, category: String): Single<List<ResultTVMovies>>

    companion object {
        const val TV_SHOW = "tv"
        const val MOVIE_SHOW = "movie"
    }
}

class DiscoverService @Inject constructor(
        private val retrofit: Retrofit,
        private val mapper: ResultMovieTVShowResponseMapper
) : DiscoverServiceFacade {
    override fun invoke(typeOfShow: String, category: String) = retrofit.create(DiscoverApi::class.java)
            .get(
                    typeOfShow,
                    category
            ).map { response ->
                val body = response.results
                if (body != null) {
                    mapper.transform(response.results)
                } else {
                    throw RuntimeException(DISCOVER_SERVICE_EXCEPTION)
                }
            }
            .subscribeOn(Schedulers.io())

    companion object {
        const val DISCOVER_SERVICE_EXCEPTION = "Discover exception error"
    }
}