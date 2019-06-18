package com.movies.cleanarchlistofmovies.data.mapper

import com.movies.cleanarchlistofmovies.data.ImageUtils
import com.movies.cleanarchlistofmovies.data.responses.ResultsMovieTVShowResponse
import com.movies.cleanarchlistofmovies.domain.ResultTVMovies
import javax.inject.Inject

class ResultMovieTVShowResponseMapper @Inject constructor(private val imageUtils: ImageUtils) : BaseListMapper<ResultsMovieTVShowResponse, ResultTVMovies>() {

    override fun transform(input: ResultsMovieTVShowResponse?): ResultTVMovies? {
        return input?.let {
            val output = ResultTVMovies(input.voteCount,
                    input.id,
                    input.video,
                    input.voteAverage,
                    input.title,
                    imageUtils.getUrl(input.posterPath),
                    input.overview)
            output
        }
    }
}