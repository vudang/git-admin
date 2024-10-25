package com.git.admin.di.stream.user

import com.git.admin.domain.stream.user.UserStreamImpl
import com.git.admin.domain.stream.user.MutableUserStream
import com.git.admin.domain.stream.user.UserStream
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UserStreamModule {
    @Provides
    @Singleton
    fun provideMutableAuthenticatedStream(): MutableUserStream {
        return UserStreamImpl()
    }

    @Provides
    @Singleton
    fun provideUserStream(userStream: MutableUserStream): UserStream {
        return userStream
    }
}