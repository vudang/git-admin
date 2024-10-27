package com.git.admin.data.repository.auth
import com.git.admin.data.datasource.local.db.AppDatabase
import com.git.admin.data.datasource.remote.NetworkService
import com.git.admin.data.model.response.DataResult
import com.git.admin.domain.model.User
import com.git.admin.domain.model.UserDetail
import com.git.admin.domain.repository.user.GetUserRepository
import com.git.admin.util.mapToAPIError
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
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
    override fun getRemoteUsers(page: Int, size: Int): Flow<DataResult<List<User>>> {
        return flow {
            try {
                val since = page * size
                val response = networkService.getListUser(size, since)
                emit(DataResult.Success(response.map { it.toModel() }))
            } catch (e: HttpException) {
                emit(DataResult.Error(e.mapToAPIError()))
            } catch (e: Exception) {
                emit(DataResult.Error(e.mapToAPIError()))
            }
        }
    }

    override fun getLocalUsers(page: Int, size: Int): Flow<DataResult<List<User>>> {
        return flow {
            try {
                val response = appDatabase.userDAO().getUsers(page, size)
                val users = response.map { it.toModel() }
                emit(DataResult.Success(users))
            } catch (e: HttpException) {
                emit(DataResult.Error(e.mapToAPIError()))
            } catch (e: Exception) {
                emit(DataResult.Error(e.mapToAPIError()))
            }
        }
    }
}
