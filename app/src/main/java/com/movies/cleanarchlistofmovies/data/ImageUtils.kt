package com.movies.cleanarchlistofmovies.data

import com.movies.cleanarchlistofmovies.data.di.ApiModule.Companion.BASE_URL_IMAGES
import javax.inject.Inject
import javax.inject.Named

class ImageUtils @Inject constructor(@Named(BASE_URL_IMAGES) val baseUrlImages: String) {

    fun getUrl(filePath: String?): String {
        return if (filePath.isNullOrEmpty()) {
            ""
        } else {
            baseUrlImages + filePath
        }
    }
}