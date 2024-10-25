package com.git.admin.presenter.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.git.admin.domain.model.UiState
import com.git.admin.domain.model.User
import com.git.admin.domain.usecase.user.GetUserDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getUserDetailUseCase: GetUserDetailUseCase
) : ViewModel() {

    private val _uiState: MutableStateFlow<UiState<Boolean>> = MutableStateFlow(UiState.None)
    val uiState: StateFlow<UiState<Boolean>> = _uiState

    private val _userList: MutableStateFlow<User?> = MutableStateFlow(null)
    val userList: StateFlow<User?> = _userList

    init {
        fetchUserList()
    }

    fun selectUser(offer: User) {
    }

    fun fetchUserList() {
        val page = 1
        _uiState.value = UiState.Loading
        viewModelScope.launch {
            // TODO: FIXME - Fetch list user here
        }
    }
}