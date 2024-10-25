package com.git.admin.presenter.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.git.admin.domain.model.UiState
import com.git.admin.domain.model.User
import com.git.admin.domain.model.UserQuery
import com.git.admin.domain.stream.user.MutableUserStream
import com.git.admin.domain.usecase.user.GetUsersUseCase
import com.git.admin.util.AppLogger
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getUsersUseCase: GetUsersUseCase,
    private val userStream: MutableUserStream
) : ViewModel() {

    private val _uiState: MutableStateFlow<UiState<List<User>>> = MutableStateFlow(UiState.None)
    val uiState: StateFlow<UiState<List<User>>> = _uiState

    private val _userList: MutableStateFlow<List<User>> = MutableStateFlow(emptyList())
    val userList: StateFlow<List<User>> = _userList
    private val _pageSize = 20

    init {
        _uiState.value = UiState.Loading
        fetchUserList()
    }

    private fun fetchUserList(query: UserQuery = UserQuery(page = 0, size = _pageSize)) {
        viewModelScope.launch {
            getUsersUseCase.execute(query).collect {
                _uiState.value = it
                if (it is UiState.Success) {
                    _userList.value += it.data
                }
            }
        }
    }

    fun loadMoreUsers() {
        val page = _userList.value.size / _pageSize
        val query = UserQuery(page = page, size = _pageSize)
        fetchUserList(query)
    }

    fun onSelectedUser(user: User) {
        AppLogger.logD("Selected user: ${user.login}")
        userStream.update(user)
    }
}
