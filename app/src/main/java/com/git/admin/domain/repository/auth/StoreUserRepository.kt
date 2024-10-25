package com.git.admin.domain.repository.auth

import com.git.admin.data.model.response.DataResult
import com.git.admin.domain.model.User
import com.git.admin.domain.model.UserDetail
import kotlinx.coroutines.flow.Flow

interface StoreUserRepository {
    fun storeUsers(users: List<User>): Flow<DataResult<Unit>>
}