package com.movies.cleanarchlistofmovies.data.mapper

abstract class BaseListMapper<in I, out O : Any> {

    fun transform(listOfInputs: List<I?>): List<O> {
        return listOfInputs.mapNotNull { input ->
            transform(input)
        }
    }

    abstract fun transform(input: I?): O?
}