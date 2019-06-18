package com.movies.cleanarchlistofmovies.data.mapper

import com.movies.cleanarchlistofmovies.data.entity.RealmResultTVMovies
import com.movies.cleanarchlistofmovies.domain.ResultTVMovies
import io.realm.RealmResults
import javax.inject.Inject

class RealmMapperResultTVMovies @Inject constructor() {

    fun transform(input: ResultTVMovies): RealmResultTVMovies {
        return RealmResultTVMovies(input.voteCount,
                input.id,
                input.video,
                input.voteAverage,
                input.title,
                input.posterPath,
                input.overview)
    }

    fun transform(input: RealmResultTVMovies): ResultTVMovies {
        return ResultTVMovies(input.voteCount,
                input.id,
                input.video,
                input.voteAverage,
                input.title,
                input.posterPath,
                input.overview)
    }

    fun transform(input: RealmResults<RealmResultTVMovies>): List<ResultTVMovies> {
        return input.map { transform(it) }
    }
}