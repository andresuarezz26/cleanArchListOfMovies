package com.movies.cleanarchlistofmovies.data.di

import com.movies.cleanarchlistofmovies.data.repositories.DiscoverMoviesRepo
import com.movies.cleanarchlistofmovies.domain.repositories.DiscoverMoviesRepoFacade
import dagger.Binds
import dagger.Module

@Module
abstract class RepositoryModule {

    @Binds
    abstract fun provideDiscoverMovies(impl: DiscoverMoviesRepo): DiscoverMoviesRepoFacade
}