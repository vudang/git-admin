package com.git.admin.domain.repository.user

import com.git.admin.data.model.response.DataResult
import com.git.admin.domain.model.User
import kotlinx.coroutines.flow.Flow

/**
 * Interface for get user repository
 */
interface GetUserRepository {
    /**
     * Get list user from remote
     *
     * @param page page number
     * @param size size of page
     * if [DataResult.Success] return list of [User]
     * if [DataResult.Error] return [APIError]
     * @see User
     * @see DataResult
     * @see APIError
     */
    fun getRemoteUsers(page: Int, size: Int): Flow<DataResult<List<User>>>

    /**
     * Get list user from local database
     *
     * @param page page number
     * @param size size of page
     * if [DataResult.Success] return list of [User]
     * if [DataResult.Error] return [APIError]
     * @see User
     * @see DataResult
     * @see APIError
     */
    fun getLocalUsers(page: Int, size: Int): Flow<DataResult<List<User>>>
}