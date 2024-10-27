package com.git.admin.di.repository

import com.git.admin.data.datasource.local.db.AppDatabase
import com.git.admin.data.datasource.remote.NetworkService
import com.git.admin.data.repository.auth.GetUserRepositoryImpl
import com.git.admin.data.repository.auth.StoreUserRepositoryImpl
import com.git.admin.domain.repository.user.GetUserRepository
import com.git.admin.domain.repository.user.StoreUserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * UserRepositoryModule for providing repository instance
 * Provide instance of GetUserRepository and StoreUserRepository using Dagger
 *
 * @see GetUserRepository
 * @see GetUserRepositoryImpl
 * @see StoreUserRepository
 * @see StoreUserRepositoryImpl
 */
@Module
@InstallIn(SingletonComponent::class)
object UserRepositoryModule {
    @Provides
    @Singleton
    fun provideGetUserRepository(database: AppDatabase, networkService: NetworkService): GetUserRepository {
        return GetUserRepositoryImpl(database, networkService)
    }

    @Provides
    @Singleton
    fun provideStoreUserRepository(database: AppDatabase): StoreUserRepository {
        return StoreUserRepositoryImpl(database)
    }
}