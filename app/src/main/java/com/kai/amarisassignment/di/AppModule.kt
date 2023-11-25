package com.kai.amarisassignment.di

import com.kai.amarisassignment.BuildConfig
import com.kai.amarisassignment.MovieApplication
import com.kai.amarisassignment.data.remote.MovieApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideMovieApplication(): MovieApplication {
        return MovieApplication()
    }


    @Provides
    @Singleton
    fun provideMovieApi(client: OkHttpClient): MovieApi {

        return Retrofit.Builder()
            .client(client)
            .baseUrl("https://www.omdbapi.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create()
    }


    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {

        val client = OkHttpClient().newBuilder()

        if (BuildConfig.DEBUG) {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            client.addInterceptor(interceptor)
        }

        return client.build()
    }
}