package com.git.admin.di.network

import com.google.gson.GsonBuilder
import com.git.admin.BuildConfig
import com.git.admin.data.datasource.remote.NetworkService
import com.git.admin.domain.stream.authentication.MutableAuthenticatedStream
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/** Created by Tony on 12/16/2024. */

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule{

    @Provides
    @Singleton
    fun provideAuthInterceptor(authenticatedStream: MutableAuthenticatedStream): AuthInterceptor {
        return AuthInterceptor(token = { authenticatedStream.user?.accessToken })
    }

    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return if (BuildConfig.DEBUG) HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        else HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.NONE)
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(logging: HttpLoggingInterceptor, authInterceptor: AuthInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(logging)
            .addInterceptor(authInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create(GsonBuilder().create())
    }

    @Provides
    @Singleton
    fun provideRetrofit(converterFactory: GsonConverterFactory, okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.API_BASE_URL)
            .addConverterFactory(converterFactory)
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): NetworkService {
        return retrofit.create(NetworkService::class.java)
    }
}