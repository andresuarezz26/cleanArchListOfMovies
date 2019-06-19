package com.movies.cleanarchlistofmovies.data.di

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named

@Module
class ApiModule {

    @Provides
    fun provideRetrofit(
            @Named(BASE_URL) baseUrl: String,
            httpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
                .client(httpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(baseUrl)
                .build()
    }

    @Provides
    fun provideHttpClient(): OkHttpClient {
        val httpClient = OkHttpClient.Builder()
        httpClient.connectTimeout(50, TimeUnit.SECONDS)
        httpClient.addInterceptor { chain ->
            val original = chain.request()
            val originalHttpUrl = original.url()

            val url = originalHttpUrl.newBuilder()
                    .addQueryParameter("api_key", "832bfab4dac5d2d785c0422f3d3f0129")
                    .build()

            // Request customization: add request headers
            val requestBuilder = original.newBuilder()
                    .url(url)

            val request = requestBuilder.build()
            chain.proceed(request)
        }
        return httpClient.build()
    }

    @Provides
    @Named(BASE_URL)
    fun provideBaseUrl(): String = "https://api.themoviedb.org/"

    @Provides
    @Named(BASE_URL_IMAGES)
    fun provideBaseUrlImages(): String = "https://image.tmdb.org/t/p/w342"

    companion object {
        const val BASE_URL = "baseUrl"
        const val BASE_URL_IMAGES = "baseUrlImages"
    }
}