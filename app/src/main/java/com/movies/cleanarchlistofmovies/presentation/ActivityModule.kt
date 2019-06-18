package com.movies.cleanarchlistofmovies.presentation

import com.movies.cleanarchlistofmovies.presentation.activity.MainActivity
import dagger.Module

@Module
abstract class ActivityModule {

    abstract fun contributeMainActivity(): MainActivity
}