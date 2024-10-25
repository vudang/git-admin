package com.git.admin.presenter.user_detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.git.admin.R
import com.git.admin.domain.model.UiState
import com.git.admin.domain.model.UserDetail
import com.git.admin.presenter.component.AppButtonIcon
import com.git.admin.presenter.component.AppButtonRegular
import com.git.admin.presenter.component.AppButtonStyle
import com.git.admin.presenter.component.AppButtonText
import com.git.admin.presenter.component.AppContainerView
import com.git.admin.presenter.component.AppIcon
import com.git.admin.presenter.component.AppText
import com.git.admin.presenter.component.AppTextField
import com.git.admin.presenter.component.AppTextFieldStyle
import com.git.admin.presenter.component.ValidationRule
import com.git.admin.presenter.component.skeleton.AppSkeletonItem
import com.git.admin.presenter.component.skeleton.AppSkeletonList
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
        modifier = Modifier.padding(),
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
            .padding(horizontal = Dimens.dp20),
        verticalArrangement = Arrangement.spacedBy(Dimens.dp20),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AppText(
            rawText = user.name,
            color = AppColor.Black,
            size = Dimens.sp24,
            weight = FontWeight.ExtraBold
        )
    }
}

@Composable
private fun HeaderView(backPress: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(Dimens.dp20)
    ) {
        AppButtonIcon(
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
            align = TextAlign.Center,
            modifier = Modifier.padding(top = Dimens.dp20)
        )
    }
}

