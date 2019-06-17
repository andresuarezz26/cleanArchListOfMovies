package com.movies.cleanarchlistofmovies.presentation.di

import com.movies.cleanarchlistofmovies.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityInjectors {

    @ContributesAndroidInjector
    abstract fun injectMainActivity(): MainActivity
}