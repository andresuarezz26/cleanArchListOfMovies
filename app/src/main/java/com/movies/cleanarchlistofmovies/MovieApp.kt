package com.movies.cleanarchlistofmovies

import android.app.Activity
import android.app.Application
import com.movies.cleanarchlistofmovies.presentation.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import io.reactivex.exceptions.UndeliverableException
import io.reactivex.plugins.RxJavaPlugins
import io.realm.Realm
import io.realm.RealmConfiguration
import javax.inject.Inject

class MovieApp : Application(), HasActivityInjector {

    @Inject lateinit var dispatchingActivityInjector: DispatchingAndroidInjector<Activity>

    override fun activityInjector(): AndroidInjector<Activity> {
        return dispatchingActivityInjector
    }

    override fun onCreate() {
        super.onCreate()
        DaggerAppComponent.builder()
                .application(this)
                .build()
                .inject(this)
        Realm.init(this)
        setRealmConfiguration()
        setRxJavaPlugins()
    }

    private fun setRxJavaPlugins() {
        RxJavaPlugins.setErrorHandler { throwable ->
            if (throwable is UndeliverableException) {
                return@setErrorHandler // ignore BleExceptions as they were surely delivered at least once
            }
        }
    }

    private fun setRealmConfiguration() {
        val realmConfiguration = RealmConfiguration.Builder()
                .schemaVersion(2)
                .build()

        Realm.setDefaultConfiguration(realmConfiguration)
    }
}
