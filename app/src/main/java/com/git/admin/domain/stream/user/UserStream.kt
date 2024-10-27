package com.git.admin.domain.stream.user

import com.git.admin.domain.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

/**
 * UserStream is a stream that provides user data.
 * It can be used to get user data from the stream.
 * The scope of this stream is read-only.
 */
interface UserStream {
    val userFlow: Flow<User?>
}

/**
 * MutableUserStream is a stream that provides user data.
 * It can be used to update user data in the stream.
 * The scope of this stream is read-write.
 */
interface MutableUserStream : UserStream {
    fun update(user: User)
    fun cleanUp()
}

/**
 * The implementation of [MutableUserStream].
 */
class UserStreamImpl: MutableUserStream {
    private var _userFlow: MutableStateFlow<User?> = MutableStateFlow(null)

    override fun update(user: User) {
        _userFlow.value = user
    }

    override val userFlow: Flow<User?>
        get() = _userFlow

    override fun cleanUp() {
        _userFlow.value = null
    }
}