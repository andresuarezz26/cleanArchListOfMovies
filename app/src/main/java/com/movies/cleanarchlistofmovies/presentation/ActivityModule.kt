package com.movies.cleanarchlistofmovies.presentation

import com.movies.cleanarchlistofmovies.MainActivity
import dagger.Module

@Module
abstract class ActivityModule {

    abstract fun contributeMainActivity(): MainActivity
}