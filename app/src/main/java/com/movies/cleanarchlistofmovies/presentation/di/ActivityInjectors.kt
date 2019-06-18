package com.movies.cleanarchlistofmovies.presentation.di

import com.movies.cleanarchlistofmovies.presentation.activity.DetailActivity
import com.movies.cleanarchlistofmovies.presentation.activity.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityInjectors {

    @ContributesAndroidInjector
    abstract fun injectMainActivity(): MainActivity

    @ContributesAndroidInjector
    abstract fun injectDetailActivity(): DetailActivity
}