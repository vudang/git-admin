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

/**
 * The ViewModel for [HomeScreen].
 * Fetch the user list from data layer and update the UI state based on the fetched data.
 *
 * @param getUsersUseCase The [GetUsersUseCase] to fetch the user list from remote.
 * @param userStream The [MutableUserStream] to update the selected user.
 */
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getUsersUseCase: GetUsersUseCase,
    private val userStream: MutableUserStream
) : ViewModel() {

    /**
     * The UI state of the home screen.
     * The UI state will be updated based on the fetched data.
     */
    private val _uiState: MutableStateFlow<UiState<List<User>>> = MutableStateFlow(UiState.None)
    val uiState: StateFlow<UiState<List<User>>> = _uiState

    /**
     * The list of users fetched from data layer.
     * The list will be updated based on the fetched data.
     */
    private val _userList: MutableStateFlow<List<User>> = MutableStateFlow(emptyList())
    val userList: StateFlow<List<User>> = _userList

    /**
     * The page size of the user list for each page.
     */
    private val _pageSize = 20

    init {
        loadUsersData()
    }

    private fun loadUsersData() {
        _uiState.value = UiState.Loading
        processFetchUserList()
    }

    /**
     * Fetch the user list from data layer
     * Update the UI state based on the fetched data
     * If the user list is fetched, update the UI state to [UiState.Success]
     * If the user list is not fetched, update the UI state to [UiState.Error]
     *
     * @param query The query to fetch the user list
     * @see UserQuery
     */
    private fun processFetchUserList(query: UserQuery = UserQuery(page = 0, size = _pageSize)) {
        viewModelScope.launch {
            getUsersUseCase.execute(query).collect {
                _uiState.value = it
                if (it is UiState.Success) {
                    _userList.value += it.data
                }
            }
        }
    }

    /**
     * Load more users when scrolled to bottom
     * Calculate the page based on the current user list size
     * And then fetch the user list based on the calculated page
     */
    fun loadMoreUsers() {
        val page = _userList.value.size / _pageSize
        val query = UserQuery(page = page, size = _pageSize)
        processFetchUserList(query)
    }

    /**
     * Navigate to user detail screen when user is selected
     * Update the selected user in [UserStream]
     *
     * @param user The selected user
     * @see User
     */
    fun onSelectedUser(user: User) {
        userStream.update(user)
    }
}
