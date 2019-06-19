package com.movies.cleanarchlistofmovies.presentation.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.bumptech.glide.Glide
import com.movies.cleanarchlistofmovies.R
import com.movies.cleanarchlistofmovies.domain.ResultTVMovies
import com.movies.cleanarchlistofmovies.presentation.extensions.observe
import com.movies.cleanarchlistofmovies.presentation.viewmodel.DetailViewModel
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_detail.imageDetailPoster
import kotlinx.android.synthetic.main.activity_detail.textDetailOverview
import kotlinx.android.synthetic.main.activity_detail.textDetailTitle
import kotlinx.android.synthetic.main.activity_detail.textDetailVoteAverage

class DetailActivity : BaseActivity() {

    private lateinit var viewModel: DetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val showId = intent.getIntExtra(SHOW_ID, 0)
        viewModel = viewModel(viewModelFactory) {
            observe(show, ::onShowReceived)
        }

        viewModel.getShow(showId)
    }

    private fun onShowReceived(show: ResultTVMovies?) {
        show?.let {
            Glide.with(imageDetailPoster).load(show.posterPath).into(imageDetailPoster);
            textDetailTitle.text = show.title
            textDetailOverview.text = show.overview
            textDetailVoteAverage.text = show.voteAverage.toString()
        }
    }

    companion object {
        fun startScreen(context: Context, id: Int): Intent {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(SHOW_ID, id)
            return intent
        }

        private const val SHOW_ID = "SHOW_ID"
    }
}