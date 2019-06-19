package com.movies.cleanarchlistofmovies.data.di

import com.movies.cleanarchlistofmovies.data.service.DiscoverMoviesAndTVServiceFacade
import com.movies.cleanarchlistofmovies.data.service.DiscoverMoviesService
import dagger.Binds
import dagger.Module

@Module
abstract class ServiceModule {

    @Binds
    abstract fun provideDiscoverMovies(impl: DiscoverMoviesService): DiscoverMoviesAndTVServiceFacade
}