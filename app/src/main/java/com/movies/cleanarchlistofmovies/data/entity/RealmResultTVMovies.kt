package com.movies.cleanarchlistofmovies.data.entity

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class RealmResultTVMovies(
        var voteCount: Int = 0,
        @PrimaryKey var id: Int = 0,
        var video: Boolean = false,
        var voteAverage: Double = 0.0,
        var title: String? = null,
        var posterPath: String? = null,
        var overview: String? = null
) : RealmObject() {
    companion object {
        const val PRIMARY_KEY = "id"
    }
}
