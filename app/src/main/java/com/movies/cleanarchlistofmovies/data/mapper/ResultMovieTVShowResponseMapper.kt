package com.movies.cleanarchlistofmovies.data.mapper

import com.movies.cleanarchlistofmovies.data.responses.ResultsMovieTVShowResponse
import com.movies.cleanarchlistofmovies.domain.ResultsMovieTVShow
import javax.inject.Inject

class ResultMovieTVShowResponseMapper @Inject constructor() : BaseListMapper<ResultsMovieTVShowResponse, ResultsMovieTVShow>() {

    override fun transform(input: ResultsMovieTVShowResponse?): ResultsMovieTVShow? {
        return input?.let {
            val output = ResultsMovieTVShow(input.voteCount,
                    input.id,
                    input.video,
                    input.voteAverage,
                    input.title,
                    input.posterPath,
                    input.overview)
            output
        }
    }
}