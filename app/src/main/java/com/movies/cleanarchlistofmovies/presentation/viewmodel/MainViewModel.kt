package com.movies.cleanarchlistofmovies.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.movies.cleanarchlistofmovies.domain.ResultTVMovies
import com.movies.cleanarchlistofmovies.domain.usecase.DiscoverTVShowAndMoviesByCategory
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import javax.inject.Inject

class MainViewModel @Inject constructor(
        private val discoverMoviesAndTV: DiscoverTVShowAndMoviesByCategory
) : ViewModel() {

    private val composite: CompositeDisposable = CompositeDisposable()

    var listOfShows = MutableLiveData<List<ResultTVMovies>>()

    fun getMoviesAndTVShows(category: String) {
        composite.add(discoverMoviesAndTV(DiscoverTVShowAndMoviesByCategory.Param(category))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { listOfShows.value = it },
                        { listOfShows.value = listOf() }
                ))

        /*

       .subscribe({ list ->
                        listOfShows.value = list
                    }, {
                        listOfShows.value = listOf()
                    }))
         */
    }

    fun dispose() {
        composite.dispose()
    }

}