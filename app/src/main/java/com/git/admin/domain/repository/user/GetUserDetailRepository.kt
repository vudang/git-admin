package com.git.admin.domain.repository.user

import com.git.admin.data.model.response.DataResult
import com.git.admin.domain.model.User
import com.git.admin.domain.model.UserDetail
import kotlinx.coroutines.flow.Flow

/**
 * Interface for get user detail repository
 */
interface GetUserDetailRepository {
    /**
     * Get user detail by username
     *
     * @param username username of user
     * @return Flow<DataResult<UserDetail>>
     * @see UserDetail
     */
    fun getUserDetail(username: String): Flow<DataResult<UserDetail>>
}