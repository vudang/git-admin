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

@HiltViewModel
class UserDetailViewModel @Inject constructor(
    private val userStream: UserStream,
    private val getUserDetailUseCase: GetUserDetailUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState<User>>(UiState.None)
    val uiState = _uiState


    init {
        fetchUserDetail()
    }

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