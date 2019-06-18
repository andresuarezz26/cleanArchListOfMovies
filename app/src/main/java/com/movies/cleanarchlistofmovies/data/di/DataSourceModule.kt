package com.movies.cleanarchlistofmovies.data.di

import com.movies.cleanarchlistofmovies.data.datasource.RealmResultTVMovieDataSource
import com.movies.cleanarchlistofmovies.data.datasource.ResultTVMovieDataSourceFacade
import dagger.Binds
import dagger.Module

@Module
abstract class DataSourceModule{

    @Binds
    abstract fun provideResultTVMovieDataSource(impl: RealmResultTVMovieDataSource): ResultTVMovieDataSourceFacade
}