package com.git.admin.domain.usecase.user

import com.git.admin.data.model.response.DataResult
import com.git.admin.domain.model.UiState
import com.git.admin.domain.model.UserDetail
import com.git.admin.domain.repository.auth.GetUserRepository
import com.git.admin.domain.stream.authentication.MutableAuthenticatedStream
import com.git.admin.domain.usecase.base.BaseUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetUserDetailUseCase @Inject constructor(
    private val getUserRepository: GetUserRepository,
    private val authenticatedUseCase: MutableAuthenticatedStream,
) : BaseUseCase<Unit, Flow<UiState<UserDetail>>>() {
    override fun execute(params: Unit): Flow<UiState<UserDetail>> {
        return getUserRepository.fetchRemoteUser()
            .map { res ->
                if (res is DataResult.Success<UserDetail>) {
                    authenticatedUseCase.update(res.data)
                }
                res.toUiState()
            }
    }
}