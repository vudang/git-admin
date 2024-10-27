package com.git.admin.di.repository

import com.git.admin.data.datasource.remote.NetworkService
import com.git.admin.data.repository.auth.GetUserDetailRepositoryImpl
import com.git.admin.domain.repository.user.GetUserDetailRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * UserDetailRepositoryModule for providing repository instance
 * Provide instance of GetUserDetailRepository using Dagger
 *
 * @see GetUserDetailRepository
 * @see GetUserDetailRepositoryImpl
 */
@Module
@InstallIn(SingletonComponent::class)
object UserDetailRepositoryModule {
    @Provides
    @Singleton
    fun provideGetUserDetailRepository(networkService: NetworkService): GetUserDetailRepository {
        return GetUserDetailRepositoryImpl(networkService)
    }
}