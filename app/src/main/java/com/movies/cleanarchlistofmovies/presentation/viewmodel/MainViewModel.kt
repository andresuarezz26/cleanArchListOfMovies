package com.movies.cleanarchlistofmovies.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.movies.cleanarchlistofmovies.domain.ResultTVMovies
import com.movies.cleanarchlistofmovies.domain.usecase.DiscoverTVShowAndMovies
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class MainViewModel @Inject constructor(
        private val discoverMoviesAndTV: DiscoverTVShowAndMovies
) : ViewModel() {

    private val composite: CompositeDisposable = CompositeDisposable()

    var listOfShows = MutableLiveData<List<ResultTVMovies>>()

    fun getMoviesAndTVShows() {
        composite.add(discoverMoviesAndTV(DiscoverTVShowAndMovies.Param("popularity.asc"))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ list ->
                    listOfShows.value = list
                }, {
                    listOfShows.value = listOf()
                }))
    }

    fun dispose() {
        // composite.dispose()
    }

}