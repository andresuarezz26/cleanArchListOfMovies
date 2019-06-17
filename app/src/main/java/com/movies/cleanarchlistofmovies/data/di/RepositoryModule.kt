package com.movies.cleanarchlistofmovies.data.di

import com.movies.cleanarchlistofmovies.data.repositories.DiscoverMoviesRepoImpl
import com.movies.cleanarchlistofmovies.domain.repositories.DiscoverMoviesRepo
import dagger.Binds
import dagger.Module

@Module
abstract class RepositoryModule {

    @Binds
    abstract fun provideDiscoverMovies(impl: DiscoverMoviesRepoImpl): DiscoverMoviesRepo
}