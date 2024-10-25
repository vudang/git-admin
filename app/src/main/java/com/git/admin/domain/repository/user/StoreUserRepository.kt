package com.git.admin.domain.repository.user

import com.git.admin.data.model.response.DataResult
import com.git.admin.domain.model.User
import kotlinx.coroutines.flow.Flow

interface StoreUserRepository {
    fun storeUsers(users: List<User>): Flow<DataResult<Unit>>
}