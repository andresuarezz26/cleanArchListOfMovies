package com.movies.cleanarchlistofmovies.presentation

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.movies.cleanarchlistofmovies.domain.usecase.DiscoverTVShowAndMovies
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainViewModel @Inject constructor(
        private val discoverMoviesAndTV: DiscoverTVShowAndMovies
) : ViewModel() {

    private val composite: CompositeDisposable = CompositeDisposable()

    var listOfShows = MutableLiveData<String>()

    fun getMoviesAndTVShows() {
        discoverMoviesAndTV()
                .observeOn(Schedulers.io())
                .subscribe({ list ->
                    listOfShows.value = "hola"
                }, {
                    Log.e("main", it.message)
                })
    }

    fun dispose() {
        // composite.dispose()
    }

}