package com.git.admin.data.repository.auth
import com.git.admin.data.datasource.remote.NetworkService
import com.git.admin.data.model.response.DataResult
import com.git.admin.domain.model.UserDetail
import com.git.admin.domain.repository.user.GetUserDetailRepository
import com.git.admin.util.handleAPIError
import com.git.admin.util.mapToAPIError
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Implementation of [GetUserDetailRepository] interface
 * Fetch user detail from network service
 * Return [DataResult] of [UserDetail]
 *
 * @param networkService instance of [NetworkService]
 * @constructor Create empty Get user detail repository impl
 * @see GetUserDetailRepository
 * @see NetworkService
 */
@Singleton
class GetUserDetailRepositoryImpl @Inject constructor(
    private val networkService: NetworkService
): GetUserDetailRepository {
    override fun getUserDetail(username: String): Flow<DataResult<UserDetail>> {
        return flow {
            val response = networkService.getUserDetail(username)
            val result: DataResult<UserDetail> = DataResult.Success(response.toModel())
            emit(result)
        }.catch { e ->
            val apiError = e.handleAPIError()
            emit(DataResult.Error(apiError))
        }
    }
}
