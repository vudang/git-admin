package com.git.admin.di.stream.user

import com.git.admin.domain.stream.user.UserStreamImpl
import com.git.admin.domain.stream.user.MutableUserStream
import com.git.admin.domain.stream.user.UserStream
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * UserStreamModule for providing stream instance
 * Provide instance of UserStream using Dagger
 *
 * @see MutableUserStream
 * @see UserStreamImpl
 */
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