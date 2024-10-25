package com.git.admin.domain.repository.user

import com.git.admin.data.model.response.DataResult
import com.git.admin.domain.model.User
import com.git.admin.domain.model.UserDetail
import kotlinx.coroutines.flow.Flow

interface GetUserDetailRepository {
    fun getUserDetail(username: String): Flow<DataResult<UserDetail>>
}