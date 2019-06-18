package com.movies.cleanarchlistofmovies.presentation

import android.app.Application
import com.movies.cleanarchlistofmovies.MovieApp
import com.movies.cleanarchlistofmovies.data.di.ApiModule
import com.movies.cleanarchlistofmovies.data.di.RepositoryModule
import com.movies.cleanarchlistofmovies.data.di.ServiceModule
import com.movies.cleanarchlistofmovies.presentation.di.ActivityInjectors
import com.movies.cleanarchlistofmovies.presentation.di.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule

@Component(
        modules = [
            AndroidSupportInjectionModule::class,
            ActivityModule::class,
            RepositoryModule::class,
            ServiceModule::class,
            ApiModule::class,
            ActivityInjectors::class,
            ViewModelModule::class]
)
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(app: MovieApp)
}