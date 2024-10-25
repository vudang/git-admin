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
import com.git.admin.presenter.component.AppButtonRegular
import com.git.admin.presenter.component.AppButtonStyle
import com.git.admin.presenter.component.AppButtonText
import com.git.admin.presenter.component.AppContainerView
import com.git.admin.presenter.component.AppIcon
import com.git.admin.presenter.component.AppText
import com.git.admin.presenter.component.AppTextField
import com.git.admin.presenter.component.AppTextFieldStyle
import com.git.admin.presenter.component.ValidationRule
import com.git.admin.presenter.router.AppRouter
import com.git.admin.presenter.theme.AppColor
import com.git.admin.util.Dimens

@Composable
fun UserDetailScreen(
    windowSize: WindowWidthSizeClass,
    router: AppRouter
) {
    AppContainerView(
        modifier = Modifier.padding(),
    ) {
        Column(
            modifier = Modifier.verticalScroll(rememberScrollState()),
        ) {
            ContentView(closeClick = { router.pop() })
        }
    }
}

@Composable
private fun ContentView(closeClick: () -> Unit) {
    Column(
        modifier = Modifier
            .padding(horizontal = Dimens.dp20),
        verticalArrangement = Arrangement.spacedBy(Dimens.dp20),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HeaderView()
        NameView()
        MailPhoneView()
        BottomButtons(closeClick = closeClick)
    }
}

@Composable
private fun BottomButtons(
    viewModel: UserDetailViewModel = hiltViewModel(),
    closeClick: () -> Unit
) {
    val isDataChanged by remember { viewModel.isDataChanged }.collectAsState()

    Row(
        modifier = Modifier.padding(top = Dimens.dp20),
        horizontalArrangement = Arrangement.spacedBy(Dimens.dp10),
    ) {
        AppButtonRegular(
            text = R.string.close,
            style = AppButtonStyle.BORDER,
            onClick = closeClick,
            modifier = Modifier.weight(1f)
        )
        AppButtonRegular(
            text = R.string.user_detail_save_change,
            onClick = { viewModel.updateUserData() },
            enable = isDataChanged,
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
private fun HeaderView() {
    AppText(
        text = R.string.user_detail_title,
        size = Dimens.sp24,
        color = AppColor.Dark,
        weight = FontWeight.ExtraBold,
        align = TextAlign.Center,
        modifier = Modifier.padding(top = Dimens.dp20)
    )
}

@Composable
private fun NameView(viewModel: UserDetailViewModel = hiltViewModel()) {
    val user by remember { viewModel.user }.collectAsState()

    AppTextField(
        label = R.string.signup_first_name,
        style = AppTextFieldStyle.BORDER,
        initValue = user?.firstName,
        validateRules = listOf(ValidationRule.REQUIRED),
        onValueChange = { viewModel.validateFirstName(it) },
    )
    AppTextField(
        label = R.string.signup_last_name,
        style = AppTextFieldStyle.BORDER,
        initValue = user?.lastName,
        validateRules = listOf(ValidationRule.REQUIRED),
        onValueChange = { viewModel.validateLastName(it) },
    )
}

@Composable
private fun MailPhoneView(viewModel: UserDetailViewModel = hiltViewModel()) {
    val user by viewModel.user.collectAsState()

    AppTextField(
        label = R.string.signup_email,
        style = AppTextFieldStyle.BORDER,
        initValue = user?.email,
        enable = false,
        validateRules = listOf(ValidationRule.EMAIL, ValidationRule.REQUIRED),
        keyboardType = KeyboardType.Email,
        onValueChange = { },
    )
    AppTextField(
        label = R.string.signup_mobile,
        style = AppTextFieldStyle.BORDER,
        initValue = user?.mobileNo,
        validateRules = listOf(ValidationRule.PHONE, ValidationRule.REQUIRED),
        keyboardType = KeyboardType.Number,
        enable = false,
        onValueChange = {  },
    )

    AppTextField(
        label = R.string.signup_address,
        style = AppTextFieldStyle.BORDER,
        initValue = user?.address,
        validateRules = listOf(ValidationRule.REQUIRED),
        onValueChange = { viewModel.validateAddress(it) },
        modifier = Modifier.padding(top = Dimens.dp20)
    )
}