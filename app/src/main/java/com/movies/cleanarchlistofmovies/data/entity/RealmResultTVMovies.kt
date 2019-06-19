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
        var overview: String? = null,
        var category: String? = null,
        var typeOfShow: String? = null
) : RealmObject() {
    companion object {
        const val PRIMARY_KEY = "id"
        const val CATEGORY = "category"
        const val TYPE_OF_SHOW = "typeOfShow"
    }
}
