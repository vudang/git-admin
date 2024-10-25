package com.git.admin.di.usecase

import com.git.admin.domain.repository.user.GetUserDetailRepository
import com.git.admin.domain.repository.user.GetUserRepository
import com.git.admin.domain.repository.user.StoreUserRepository
import com.git.admin.domain.usecase.user.GetUserDetailUseCase
import com.git.admin.domain.usecase.user.GetUsersUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object UserDetailUseCaseModule {
    @Provides
    fun provideGetUserDetailUseCase(
        repository: GetUserDetailRepository
    ): GetUserDetailUseCase {
        return GetUserDetailUseCase(repository)
    }
}