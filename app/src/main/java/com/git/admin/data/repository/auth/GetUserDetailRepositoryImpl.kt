package com.git.admin.data.repository.auth
import com.git.admin.data.datasource.local.db.AppDatabase
import com.git.admin.data.datasource.remote.NetworkService
import com.git.admin.data.model.response.DataResult
import com.git.admin.domain.model.User
import com.git.admin.domain.model.UserDetail
import com.git.admin.domain.repository.user.GetUserDetailRepository
import com.git.admin.domain.repository.user.GetUserRepository
import com.git.admin.util.mapToAPIError
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetUserDetailRepositoryImpl @Inject constructor(
    private val networkService: NetworkService
): GetUserDetailRepository {
    override fun getUserDetail(username: String): Flow<DataResult<UserDetail>> {
        return flow {
            try {
                val response = networkService.getUserDetail(username)
                emit(DataResult.Success(response.toModel()))
            } catch (e: HttpException) {
                emit(DataResult.Error(e.mapToAPIError()))
            } catch (e: Exception) {
                emit(DataResult.Error(e.mapToAPIError()))
            }
        }
    }
}
