package com.git.admin.presenter.user_detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import com.git.admin.R
import com.git.admin.domain.model.UiState
import com.git.admin.domain.model.UserDetail
import com.git.admin.presenter.component.AppButtonIcon
import com.git.admin.presenter.component.AppContainerView
import com.git.admin.presenter.component.AppText
import com.git.admin.presenter.component.skeleton.AppSkeletonItem
import com.git.admin.presenter.home.UserCell
import com.git.admin.presenter.router.AppRouter
import com.git.admin.presenter.theme.AppColor
import com.git.admin.util.Dimens

@Composable
fun UserDetailScreen(
    windowSize: WindowWidthSizeClass,
    router: AppRouter,
    viewModel: UserDetailViewModel = hiltViewModel()
) {
    val uiState by remember { viewModel.uiState }.collectAsState()

    fun goBack() {
        router.pop()
    }

    AppContainerView(
        customTopBar = { HeaderView(backPress = { goBack() }) }
    ) {
        when (uiState) {
            is UiState.Loading -> {
                LoadingView()
            }
            is UiState.Error -> {
                val error = (uiState as UiState.Error).errorMessage
                EmptyView(message = error)
            }
            is UiState.Success -> {
                val user = (uiState as UiState.Success).data
                if (user is UserDetail) {
                    ContentView(user)
                }
            }
            else -> {
                EmptyView(message = "No data found")
            }
        }
    }
}


@Composable
private fun HeaderView(backPress: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = Dimens.dp20),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(Dimens.dp20)
    ) {
        AppButtonIcon(
            modifier = Modifier.padding(start = Dimens.dp10),
            iconDrawable = R.drawable.ic_back,
            padding = Dimens.dp0,
        ) {
            backPress()
        }
        AppText(
            text = R.string.user_detail_title,
            size = Dimens.sp24,
            color = AppColor.Dark,
            weight = FontWeight.ExtraBold,
            align = TextAlign.Center
        )
    }
}

@Composable
private fun LoadingView() {
    AppSkeletonItem()
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


@Composable
private fun ContentView(user: UserDetail) {
    Column(
        modifier = Modifier
            .padding(horizontal = Dimens.dp20)
            .padding(top = Dimens.dp20),
        verticalArrangement = Arrangement.spacedBy(Dimens.dp20),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        UserInfoSection(user)
        FollowSection(user)
        BlogSection(user)
    }
}

@Composable
private fun BlogSection(user: UserDetail) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(Dimens.dp8),
        horizontalAlignment = Alignment.Start
    ) {
        AppText(
            text = R.string.user_detail_blog,
            weight = FontWeight.Bold,
            size = Dimens.sp20,
            color = AppColor.Black
        )
        AppText(
            rawText = user.blog,
            color = AppColor.Gray,
            size = Dimens.sp16
        )
    }
}

@Composable
private fun FollowSection(user: UserDetail) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        StatItem(
            icon = Icons.Default.AccountCircle,
            count = "${user.followers}+",
            label = stringResource(id = R.string.user_detail_follower)
        )
        StatItem(
            icon = Icons.Default.Person,
            count = "${user.following}+",
            label = stringResource(id = R.string.user_detail_following)
        )
    }
}

@Composable
private fun UserInfoSection(user: UserDetail) {
    UserCell(
        avatarUrl = user.avatarUrl,
        name = user.name,
        highlights = {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.LocationOn,
                    contentDescription = null,
                    modifier = Modifier.size(Dimens.dp16),
                    tint = AppColor.Gray
                )
                Spacer(modifier = Modifier.width(Dimens.dp4))
                AppText(
                    rawText = user.location,
                    color = AppColor.Gray,
                    size = Dimens.sp16
                )
            }
        }
    )
}

@Composable
private fun StatItem(
    icon: ImageVector,
    count: String,
    label: String
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(Dimens.dp4)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            modifier = Modifier.size(Dimens.dp30)
        )
        AppText(
            rawText = count,
            weight = FontWeight.Bold,
            color = AppColor.Black
        )
        AppText(
            rawText = label,
            color = AppColor.Gray
        )
    }
}

