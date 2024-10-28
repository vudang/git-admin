package com.git.admin.data.repository.auth

import com.git.admin.data.datasource.local.db.AppDatabase
import com.git.admin.data.model.response.DataResult
import com.git.admin.domain.model.User
import com.git.admin.domain.model.toStore
import com.git.admin.domain.repository.user.StoreUserRepository
import com.git.admin.util.handleAPIError
import com.git.admin.util.mapToAPIError
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Implementation of [StoreUserRepository] interface
 * Store user to local database
 * Return [DataResult] of [Unit]
 *
 * @param appDatabase instance of [AppDatabase]
 * @constructor Create empty Store user repository impl
 * @see StoreUserRepository
 * @see AppDatabase
 */
@Singleton
class StoreUserRepositoryImpl @Inject constructor(
    private val appDatabase: AppDatabase,
): StoreUserRepository {

    override fun storeUsers(users: List<User>): Flow<DataResult<Unit>> {
        return flow {
            val userStore = users.map { it.toStore() }
            appDatabase.userDAO().insertUsers(userStore)
            val result: DataResult<Unit> = DataResult.Success(Unit)
            emit(result)
        }.catch { e ->
            val apiError = e.handleAPIError()
            emit(DataResult.Error(apiError))
        }
    }
}
