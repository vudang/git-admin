package com.git.admin.domain.usecase.user

import com.git.admin.data.model.response.DataResult
import com.git.admin.domain.model.UiState
import com.git.admin.domain.model.User
import com.git.admin.domain.model.UserQuery
import com.git.admin.domain.repository.user.GetUserRepository
import com.git.admin.domain.repository.user.StoreUserRepository
import com.git.admin.domain.usecase.base.BaseUseCase
import com.git.admin.util.AppLogger
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * Use case for fetching list of users
 * Fetch list of users from local database, if not found fetch from remote
 * Store the fetched data to local database
 * Return the fetched data
 *
 * @param getUserRepository GetUserRepository
 * @param storeUserRepository StoreUserRepository
 * @see GetUserRepository
 * @see StoreUserRepository
 */
class GetUsersUseCase @Inject constructor(
    private val getUserRepository: GetUserRepository,
    private val storeUserRepository: StoreUserRepository,
) : BaseUseCase<UserQuery, Flow<UiState<List<User>>>>() {

    /**
     * Execute the main task for use case
     * Fetch list of users from local database, if not found fetch from remote
     * Store the fetched data to local database
     * Return the fetched data
     *
     * @param params UserQuery
     * @return Flow<UiState<List<User>>> Flow of UiState
     * @see UserQuery
     * @see User
     */
    @OptIn(ExperimentalCoroutinesApi::class)
    override fun execute(params: UserQuery): Flow<UiState<List<User>>> {
        AppLogger.logD("[GetUsersUseCase]: Fetching users at page: ${params.page}, size: ${params.size}")
        val localFlow = getUserRepository.getLocalUsers(params.page, params.size)
        val remoteFlow = getUserRepository.getListUser(params.page, params.size)
        return localFlow
            .flatMapConcat { dataResult ->
                when (dataResult) {
                    is DataResult.Success<List<User>> -> {
                        if (dataResult.data.isEmpty()) {
                            AppLogger.logD("[GetUsersUseCase]: No local data, fetching remote data")
                            remoteFlow
                        } else {
                            AppLogger.logD("[GetUsersUseCase]: Local data found, returning local data")
                            flowOf(dataResult)
                        }
                    }
                    else -> remoteFlow
                }
            }
            .flatMapConcat { dataResult ->
                when (dataResult) {
                    is DataResult.Success<List<User>> -> storeUsers(dataResult.data)
                    else -> flowOf(dataResult)
                }
            }
            .map { res -> res.toUiState() }
    }

    /**
     * Store list users to local database
     * @param users List of users to store
     * @return Flow<DataResult<List<User>>> Flow of DataResult
     */
    private fun storeUsers(users: List<User>): Flow<DataResult<List<User>>> {
        return storeUserRepository.storeUsers(users)
            .map { DataResult.Success(users) }
    }
}