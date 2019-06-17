package com.movies.cleanarchlistofmovies.data.service

import com.movies.cleanarchlistofmovies.data.responses.ResultsMovieTVShowResponse
import io.reactivex.Single

interface GetTVService {

    operator fun invoke(): Single<ResultsMovieTVShowResponse>
}