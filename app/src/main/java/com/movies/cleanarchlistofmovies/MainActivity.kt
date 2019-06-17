package com.movies.cleanarchlistofmovies

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.movies.cleanarchlistofmovies.domain.usecase.DiscoverMovies
import dagger.android.AndroidInjection
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var discoverMovies: DiscoverMovies

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()
        discoverMovies().subscribe({ list ->
            Log.i("main", "size ${list.size}")
            Log.i("main", "error")
        }, {
            Log.e("main", it.message)
        })
    }
}
