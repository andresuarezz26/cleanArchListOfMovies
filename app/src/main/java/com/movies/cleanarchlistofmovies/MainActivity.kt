package com.movies.cleanarchlistofmovies

import android.os.Bundle
import android.widget.Toast
import com.movies.cleanarchlistofmovies.presentation.BaseActivity
import com.movies.cleanarchlistofmovies.presentation.MainViewModel
import com.movies.cleanarchlistofmovies.presentation.extensions.observe
import dagger.android.AndroidInjection

class MainActivity : BaseActivity() {

    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainViewModel = viewModel(viewModelFactory) {
            observe(listOfShows, ::onGetMoviesAndTVShows)
        }
    }

    override fun onResume() {
        super.onResume()
        mainViewModel.getMoviesAndTVShows()
    }

    fun onGetMoviesAndTVShows(result: String?) {
        Toast.makeText(this, result, Toast.LENGTH_LONG).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        mainViewModel.dispose()
    }
}
