package com.movies.cleanarchlistofmovies.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.movies.cleanarchlistofmovies.domain.ResultTVMovies
import com.movies.cleanarchlistofmovies.domain.usecase.GetShowById
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class DetailViewModel @Inject constructor(private val getShowById: GetShowById) : ViewModel() {

    var show = MutableLiveData<ResultTVMovies>()

    internal var compositeDisposable = CompositeDisposable()

    fun getShow(id: Int) {
        compositeDisposable.add(getShowById(id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { show.value = it },
                        { Log.e("error", it.message ?: "") }
                ))
    }

    fun dispose() {
        compositeDisposable.dispose()
    }
}