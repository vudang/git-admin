package com.git.admin.data.repository.auth
import com.git.admin.data.datasource.local.db.AppDatabase
import com.git.admin.data.datasource.remote.NetworkService
import com.git.admin.data.model.response.DataResult
import com.git.admin.domain.model.User
import com.git.admin.domain.repository.user.GetUserRepository
import com.git.admin.util.handleAPIError
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Implementation of [GetUserRepository] interface
 * Fetch user from network service
 * Return [DataResult] of [List<User>]
 *
 * @param appDatabase instance of [AppDatabase]
 * @param networkService instance of [NetworkService]
 * @constructor Create empty Get user repository impl
 * @see GetUserRepository
 * @see AppDatabase
 * @see NetworkService
 */
@Singleton
class GetUserRepositoryImpl @Inject constructor(
    private val appDatabase: AppDatabase,
    private val networkService: NetworkService
): GetUserRepository {
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
    override fun getRemoteUsers(page: Int, size: Int): Flow<DataResult<List<User>>> {
        return flow {
            val since = page * size
            val response = networkService.getListUser(size, since)
            val result: DataResult<List<User>> = DataResult.Success(response.map { it.toModel() })
            emit(result)
        }.catch { e ->
            val apiError = e.handleAPIError()
            emit(DataResult.Error(apiError))
        }
    }

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
    override fun getLocalUsers(page: Int, size: Int): Flow<DataResult<List<User>>> {
        return flow {
            val response = appDatabase.userDAO().getUsers(page, size)
            val users = response.map { it.toModel() }
            val result: DataResult<List<User>> = DataResult.Success(users)
            emit(result)
        }.catch { e ->
            val apiError = e.handleAPIError()
            emit(DataResult.Error(apiError))
        }
    }
}
