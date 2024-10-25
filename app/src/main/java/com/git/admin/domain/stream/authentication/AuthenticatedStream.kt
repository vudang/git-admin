package com.git.admin.domain.stream.authentication

import com.git.admin.domain.model.User
import com.git.admin.domain.model.UserDetail
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

interface AuthenticatedStream {
    val user: User?
    val userDetail: UserDetail?
    val userFlow: Flow<User?>
}

interface MutableAuthenticatedStream : AuthenticatedStream {
    fun update(user: User)
    fun update(userDetail: UserDetail)
    fun cleanUp()
}

class AuthenticatedStreamImpl: MutableAuthenticatedStream {
    private var _userData: User? = null
    private var _userDetail: UserDetail? = null
    private var _userFlow: MutableStateFlow<User?> = MutableStateFlow(null)

    override fun update(user: User) {
        _userData = user
        _userFlow.value = user
    }

    override fun update(userDetail: UserDetail) {
        _userDetail = userDetail
    }

    override val user: User?
        get() = _userData

    override val userDetail: UserDetail?
        get() = _userDetail

    override val userFlow: Flow<User?>
        get() = _userFlow

    override fun cleanUp() {
        _userData = null
        _userDetail = null
        _userFlow.value = null
    }
}