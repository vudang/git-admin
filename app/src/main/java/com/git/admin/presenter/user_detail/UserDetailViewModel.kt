package com.git.admin.presenter.user_detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.git.admin.domain.model.UiState
import com.git.admin.domain.model.User
import com.git.admin.domain.stream.user.MutableUserStream
import com.git.admin.domain.stream.user.UserStream
import com.git.admin.domain.usecase.user.GetUserDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * The ViewModel for [UserDetailScreen].
 * Get the current user from [UserStream] and fetch the user detail from remote.
 * Update the UI state based on the fetched data.
 *
 * @param userStream The [UserStream] to get the current user.
 * @param getUserDetailUseCase The [GetUserDetailUseCase] to get the user detail from remote
 */
@HiltViewModel
class UserDetailViewModel @Inject constructor(
    private val userStream: UserStream,
    private val getUserDetailUseCase: GetUserDetailUseCase,
) : ViewModel() {

    /**
     * The UI state of the user detail screen.
     * The UI state will be updated based on the fetched data.
     */
    private val _uiState = MutableStateFlow<UiState<User>>(UiState.None)
    val uiState = _uiState


    init {
        fetchUserDetail()
    }

    /**
     * Fetch the user detail from remote
     * Update the UI state based on the fetched data
     * If the user is null, update the UI state to [UiState.None]
     * If the user is not null, update the UI state to [UiState.Success]
     * If the user detail is fetched, update the UI state to [UiState.Success]
     * If the user detail is not fetched, update the UI state to [UiState.Error]
     */
    @OptIn(ExperimentalCoroutinesApi::class)
    private fun fetchUserDetail() {
        viewModelScope.launch {
            userStream.userFlow
                .flatMapConcat { user ->
                    if (user != null) {
                        _uiState.value = UiState.Success(user)
                        getUserDetailUseCase.execute(user)
                    } else {
                        flowOf(UiState.None)
                    }
                }
                .collect {
                    _uiState.value = it
                }
        }
    }
}