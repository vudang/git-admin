package com.git.admin.domain.repository.auth

import com.git.admin.data.model.response.DataResult
import com.git.admin.domain.model.User
import com.git.admin.domain.model.UserDetail
import kotlinx.coroutines.flow.Flow

interface GetUserRepository {
    fun getLocalUser(): Flow<DataResult<User>>
    fun fetchRemoteUser(): Flow<DataResult<UserDetail>>
}