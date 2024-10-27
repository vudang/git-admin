package com.git.admin.domain.usecase.user

import com.git.admin.domain.model.UiState
import com.git.admin.domain.model.User
import com.git.admin.domain.model.UserDetail
import com.git.admin.domain.repository.user.GetUserDetailRepository
import com.git.admin.domain.usecase.base.BaseUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * Get user detail by username use case
 * Fetch user detail from remote
 * Return the fetched data
 * Return error if username is empty
 *
 * @param getUserRepository GetUserDetailRepository
 * @see GetUserDetailRepository
 * @see User
 * @see UserDetail
 */

class GetUserDetailUseCase @Inject constructor(
    private val getUserRepository: GetUserDetailRepository,
) : BaseUseCase<User, Flow<UiState<UserDetail>>>() {

    /**
     * Get user detail by username
     * If username is empty, return error
     * Unless, Fetch user detail from remote
     *
     * @param params User
     * @return Flow<UiState<UserDetail>> Flow of UiState
     * @see User
     * @see UserDetail
     */
    override fun execute(params: User): Flow<UiState<UserDetail>> {
        val userName = params.login
        if (userName.isNullOrEmpty()) {
            return flowOf(UiState.Error("Username is empty"))
        }

        return getUserRepository.getUserDetail(userName)
            .map { res -> res.toUiState() }
    }
}