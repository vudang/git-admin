package com.git.admin.domain.repository.user

import com.git.admin.data.model.response.DataResult
import com.git.admin.domain.model.User
import kotlinx.coroutines.flow.Flow

/**
 * Interface for store user repository
 */
interface StoreUserRepository {
    /**
     * Store list user to local database
     *
     * @param users list of user
     * @return Flow<DataResult<Unit>>
     * @see User
     */
    fun storeUsers(users: List<User>): Flow<DataResult<Unit>>
}