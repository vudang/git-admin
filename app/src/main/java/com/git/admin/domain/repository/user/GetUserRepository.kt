package com.git.admin.domain.repository.user

import com.git.admin.data.model.response.DataResult
import com.git.admin.domain.model.User
import com.git.admin.domain.model.UserDetail
import kotlinx.coroutines.flow.Flow

interface GetUserRepository {
    fun getListUser(page: Int, size: Int): Flow<DataResult<List<User>>>
    fun getLocalUsers(page: Int, size: Int): Flow<DataResult<List<User>>>
}