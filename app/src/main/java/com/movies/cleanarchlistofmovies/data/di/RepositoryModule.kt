package com.movies.cleanarchlistofmovies.data.di

import com.movies.cleanarchlistofmovies.data.repositories.DiscoverMoviesTVRepo
import com.movies.cleanarchlistofmovies.data.repositories.GetShowByIdRepo
import com.movies.cleanarchlistofmovies.domain.repositories.DiscoverMoviesTVRepoFacade
import com.movies.cleanarchlistofmovies.domain.repositories.GetShowByIdRepoFacade
import dagger.Binds
import dagger.Module

@Module
abstract class RepositoryModule {

    @Binds
    abstract fun provideDiscoverMovies(impl: DiscoverMoviesTVRepo): DiscoverMoviesTVRepoFacade

    @Binds
    abstract fun provideGetShowById(impl: GetShowByIdRepo): GetShowByIdRepoFacade

}