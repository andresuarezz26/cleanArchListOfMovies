package com.movies.cleanarchlistofmovies.data.di

import com.movies.cleanarchlistofmovies.data.service.DiscoverMoviesServiceFacade
import com.movies.cleanarchlistofmovies.data.service.DiscoverMoviesService
import dagger.Binds
import dagger.Module

@Module
abstract class ServiceModule {

    @Binds
    abstract fun provideDiscoverMovies(impl: DiscoverMoviesService): DiscoverMoviesServiceFacade
}