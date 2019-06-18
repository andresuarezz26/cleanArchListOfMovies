package com.movies.cleanarchlistofmovies.data.api

import com.movies.cleanarchlistofmovies.data.responses.BaseResultsMovieTVShowResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface DiscoverApi {

    @GET("/3/discover/{typeOfShow}")
    fun get(
            @Path("typeOfShow") typeOfShow: String,
            @Query("sort_by") sortBy: String
    ): Single<BaseResultsMovieTVShowResponse>
}