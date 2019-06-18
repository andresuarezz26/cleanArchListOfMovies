package com.movies.cleanarchlistofmovies.presentation.activity

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.movies.cleanarchlistofmovies.R
import com.movies.cleanarchlistofmovies.domain.ResultTVMovies
import com.movies.cleanarchlistofmovies.presentation.BaseActivity
import com.movies.cleanarchlistofmovies.presentation.ResultsTVMoviesAdapter
import com.movies.cleanarchlistofmovies.presentation.extensions.observe
import com.movies.cleanarchlistofmovies.presentation.viewmodel.MainViewModel
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.recyclerViewResultsTVMovies

class MainActivity : BaseActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var mainAdapter: ResultsTVMoviesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainAdapter = ResultsTVMoviesAdapter { onItemClick(it) }
        recyclerViewResultsTVMovies.apply {
            layoutManager = LinearLayoutManager(context)
            this.adapter = mainAdapter
        }
        viewModel = viewModel(viewModelFactory) {
            observe(listOfShows, ::onGetMoviesAndTVShows)
        }
    }

    private fun onItemClick(showId: Int) {
        startActivity(DetailActivity.startScreen(this.applicationContext, showId))
    }

    override fun onResume() {
        super.onResume()
        viewModel.getMoviesAndTVShows()
    }

    private fun onGetMoviesAndTVShows(list: List<ResultTVMovies>?) {
        list?.let {
            mainAdapter.addAll(list)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.dispose()
    }
}
