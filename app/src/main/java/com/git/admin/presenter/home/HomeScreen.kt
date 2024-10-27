package com.git.admin.presenter.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.git.admin.domain.model.UiState
import com.git.admin.domain.model.User
import com.git.admin.presenter.component.AppContainerView
import com.git.admin.presenter.component.AppText
import com.git.admin.presenter.component.skeleton.AppSkeletonList
import com.git.admin.presenter.router.AppRoute
import com.git.admin.presenter.router.AppRouter
import com.git.admin.presenter.theme.AppColor
import com.git.admin.util.Dimens
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter

@Composable
fun HomeScreen(
    windowSize: WindowWidthSizeClass,
    router: AppRouter,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val uiState by remember { viewModel.uiState }.collectAsState()
    val userList by remember { viewModel.userList }.collectAsState()

    fun navigateToUserDetail() {
        router.push(AppRoute.USER_DETAIL)
    }

    AppContainerView(
        modifier = Modifier.padding()
    ) {
        when (uiState) {
            is UiState.Loading -> {
                LoadingView()
            }
            is UiState.Success -> {
                ListUsers(
                    users = userList,
                    paddingValues = it,
                    selectedUser = { navigateToUserDetail() }
                )
            }
            is UiState.Error -> {
                val error = (uiState as UiState.Error).errorMessage
                EmptyView(message = error ?: "An error occurred")
            }
            else -> {
                EmptyView(message = "No users found")
            }
        }
    }
}

@Composable
private fun LoadingView() {
    AppSkeletonList()
}

@Composable
private fun EmptyView(message: String) {
    AppText(
        modifier = Modifier.padding(Dimens.dp30),
        rawText = message,
        size = Dimens.sp16,
        color = AppColor.Gray,
        weight = FontWeight.Medium
    )
}

/**
 * List of users Compose
 * Display list of users
 * Load more users when scrolled to bottom
 *
 * @param users List of users
 * @param paddingValues PaddingValues
 * @param viewModel HomeViewModel
 */
@OptIn(FlowPreview::class)
@Composable
private fun ListUsers(
    users: List<User>,
    paddingValues: PaddingValues,
    selectedUser: () -> Unit = {},
    viewModel: HomeViewModel = hiltViewModel()
) {
    val listState = rememberLazyListState()
    LaunchedEffect(listState) {
        snapshotFlow { listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index }
            .filter { it != null && it >= users.size - 1 }
            .distinctUntilChanged()
            .debounce(500)
            .collect {
                viewModel.loadMoreUsers()
            }
    }

    LazyColumn(
        modifier = Modifier
            .padding(paddingValues)
            .padding(horizontal = Dimens.dp20),
        state = listState
    ) {
        items(
            users,
        ) { user ->
            UserCell(
                modifier = Modifier.clickable {
                    viewModel.onSelectedUser(user)
                    selectedUser()
                },
                avatarUrl = user.avatarUrl,
                name = user.login,
                highlights = {
                    AppText(
                        rawText = user.htmlUrl,
                        size = 16.sp,
                        color = AppColor.Blue
                    )
                }
            )
        }
    }
}