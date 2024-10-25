package com.git.admin.data.repository.auth
import com.git.admin.data.datasource.local.db.AppDatabase
import com.git.admin.data.datasource.remote.NetworkService
import com.git.admin.data.model.base.APIError
import com.git.admin.data.model.response.DataResult
import com.git.admin.domain.model.User
import com.git.admin.domain.model.UserDetail
import com.git.admin.domain.repository.auth.GetUserRepository
import com.git.admin.util.mapToAPIError
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetUserRepositoryImpl @Inject constructor(
    private val appDatabase: AppDatabase,
    private val networkService: NetworkService
): GetUserRepository {
    override fun getListUser(page: Int, size: Int): Flow<DataResult<List<User>>> {
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
