package com.git.admin.domain.stream.user

import com.git.admin.domain.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

interface UserStream {
    val userFlow: Flow<User?>
}

interface MutableUserStream : UserStream {
    fun update(user: User)
    fun cleanUp()
}

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