package com.git.admin.presenter.home

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.navigation.compose.hiltViewModel
import com.git.admin.R
import com.git.admin.domain.model.UiState
import com.git.admin.domain.model.User
import com.git.admin.presenter.component.AppContainerView
import com.git.admin.presenter.component.AppText
import com.git.admin.presenter.component.skeleton.AppSkeletonList
import com.git.admin.presenter.router.AppRoute
import com.git.admin.presenter.router.AppRouter
import com.git.admin.presenter.theme.AppColor
import com.git.admin.util.Dimens

@Composable
fun HomeScreen(
    windowSize: WindowWidthSizeClass,
    router: AppRouter,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val uiState by remember { viewModel.uiState }.collectAsState()

    fun onUserClick(user: User) {
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
                    users = (uiState as UiState.Success<List<User>>).data,
                    paddingValues = it
                )
            }
            else -> {
                EmptyView()
            }
        }
    }
}

@Composable
private fun LoadingView() {
    AppSkeletonList()
}

@Composable
private fun EmptyView() {
    AppText(
        text = R.string.empty,
        size = Dimens.sp16,
        color = AppColor.Gray,
        weight = FontWeight.Medium
    )
}

@Composable
private fun ListUsers(users: List<User>, paddingValues: PaddingValues) {
    LazyColumn(
        modifier = Modifier.padding(paddingValues),
        state = rememberLazyListState()
    ) {
        items(
            users,
            key = { user -> user.id ?: "" }
        ) { user ->
            UserCell(user = user)
        }
    }
}