package com.movies.cleanarchlistofmovies.data.di

import com.movies.cleanarchlistofmovies.data.service.DiscoverMoviesService
import com.movies.cleanarchlistofmovies.data.service.DiscoverMoviesServiceImpl
import dagger.Binds
import dagger.Module

@Module
abstract class ServiceModule {

    @Binds
    abstract fun provideDiscoverMovies(impl: DiscoverMoviesServiceImpl): DiscoverMoviesService
}