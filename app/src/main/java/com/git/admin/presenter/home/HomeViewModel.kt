package com.git.admin.presenter.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.git.admin.domain.model.UiState
import com.git.admin.domain.model.User
import com.git.admin.domain.model.UserQuery
import com.git.admin.domain.usecase.user.GetUsersUseCase
import com.git.admin.util.AppLogger
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getUsersUseCase: GetUsersUseCase
) : ViewModel() {

    private val _uiState: MutableStateFlow<UiState<List<User>>> = MutableStateFlow(UiState.None)
    val uiState: StateFlow<UiState<List<User>>> = _uiState

    private val _userList: MutableStateFlow<List<User>> = MutableStateFlow(emptyList())
    val userList: StateFlow<List<User>> = _userList

    init {
        fetchUserList()
    }

    private fun fetchUserList(query: UserQuery = UserQuery(page = 1, size = 30)) {
        _uiState.value = UiState.Loading
        viewModelScope.launch {
            getUsersUseCase.execute(query).collect {
                _uiState.value = it
            }
        }
    }

    fun loadMoreUsers() {
        val query = UserQuery(page = 2, size = 10)
        fetchUserList(query)
    }
}
