package com.movies.cleanarchlistofmovies.data.di

import com.movies.cleanarchlistofmovies.data.service.DiscoverServiceFacade
import com.movies.cleanarchlistofmovies.data.service.DiscoverService
import dagger.Binds
import dagger.Module

@Module
abstract class ServiceModule {

    @Binds
    abstract fun provideDiscoverMovies(impl: DiscoverService): DiscoverServiceFacade
}