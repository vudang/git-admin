package com.git.admin.di.usecase

import com.git.admin.domain.repository.user.GetUserRepository
import com.git.admin.domain.repository.user.StoreUserRepository
import com.git.admin.domain.usecase.user.GetUsersUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

/**
 * UserUseCaseModule for providing use case instance
 * Provide instance of GetUsersUseCase using Dagger
 *
 * @see GetUsersUseCase
 */
@Module
@InstallIn(ViewModelComponent::class)
object UserUseCaseModule {
    @Provides
    fun provideGetUserUseCase(
        repository: GetUserRepository,
        storeUserRepository: StoreUserRepository
    ): GetUsersUseCase {
        return GetUsersUseCase(repository, storeUserRepository)
    }
}