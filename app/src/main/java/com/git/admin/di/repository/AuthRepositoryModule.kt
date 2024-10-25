package com.git.admin.di.repository

import com.git.admin.data.datasource.local.db.AppDatabase
import com.git.admin.data.datasource.remote.NetworkService
import com.git.admin.data.repository.auth.GetUserRepositoryImpl
import com.git.admin.domain.repository.auth.GetUserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AuthRepositoryModule {
    @Provides
    @Singleton
    fun provideGetUserRepository(database: AppDatabase, networkService: NetworkService): GetUserRepository {
        return GetUserRepositoryImpl(database, networkService)
    }
}