package com.movies.cleanarchlistofmovies.presentation.activity

import android.os.Bundle
import android.view.View
import androidx.core.app.ActivityOptionsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.tabs.TabLayout
import com.movies.cleanarchlistofmovies.R
import com.movies.cleanarchlistofmovies.data.Constants
import com.movies.cleanarchlistofmovies.domain.ResultTVMovies
import com.movies.cleanarchlistofmovies.presentation.adapter.ResultsTVMoviesAdapter
import com.movies.cleanarchlistofmovies.presentation.extensions.observe
import com.movies.cleanarchlistofmovies.presentation.viewmodel.MainViewModel
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.recyclerViewResultsTVMovies
import kotlinx.android.synthetic.main.activity_main.tabsMainActivity

class MainActivity : BaseActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var mainAdapter: ResultsTVMoviesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initTabs()
        listenTabEvents()
        initAdapter()
        viewModel = viewModel(viewModelFactory) {
            observe(listOfShows, ::onGetMoviesAndTVShows)
        }
        viewModel.getMoviesAndTVShows(Constants.CATEGORY_POPULAR)
    }

    private fun initAdapter() {
        mainAdapter = ResultsTVMoviesAdapter { (id, view) -> onItemClick(id, view) }
        recyclerViewResultsTVMovies.apply {
            layoutManager = LinearLayoutManager(context)
            this.adapter = mainAdapter
        }
    }

    private fun initTabs() {
        tabsMainActivity.addTab(tabsMainActivity.newTab().setText("Popular"))
        tabsMainActivity.addTab(tabsMainActivity.newTab().setText("Top Rated"))
        tabsMainActivity.addTab(tabsMainActivity.newTab().setText("Upcoming "))
    }

    private fun listenTabEvents() {
        tabsMainActivity.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabUnselected(p0: TabLayout.Tab?) {
                // Do nothing..
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                tab?.position?.let {
                    when (it) {
                        0 -> viewModel.getMoviesAndTVShows(Constants.CATEGORY_POPULAR)
                        1 -> viewModel.getMoviesAndTVShows(Constants.CATEGORY_TOP_RATED)
                        else -> viewModel.getMoviesAndTVShows(Constants.CATEGORY_RELEASE_DATE)
                    }
                }
            }

            override fun onTabReselected(p0: TabLayout.Tab?) {
                // Do nothing..
            }

        })
    }

    private fun onItemClick(showId: Int, view: View) {
        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(this, view, "path_transition")
        startActivity(DetailActivity.startScreen(this.applicationContext, showId), options.toBundle())
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
