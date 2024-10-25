package com.git.admin.di.usecase

import com.git.admin.domain.repository.auth.GetUserRepository
import com.git.admin.domain.repository.auth.StoreUserRepository
import com.git.admin.domain.stream.authentication.MutableAuthenticatedStream
import com.git.admin.domain.usecase.user.GetUsersUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

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