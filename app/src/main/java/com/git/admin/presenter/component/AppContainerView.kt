package com.git.admin.presenter.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import com.git.admin.R
import com.git.admin.presenter.theme.AppColor
import com.git.admin.util.Dimens

@Composable
fun AppContainerView(
    modifier: Modifier = Modifier,
    customTopBar: @Composable (() -> Unit)? = null,
    content: @Composable (PaddingValues) -> Unit
) {
    Scaffold(
        modifier = modifier
            .fillMaxWidth()
            .systemBarsPadding(),
        topBar = {
            if (customTopBar != null) customTopBar() else AppTopBar(
                modifier = Modifier
                    .padding(start = Dimens.dp20)
                    .padding(end = Dimens.dp10)
            )
        }
    ) {
        content(it)
    }
}

@Preview(showSystemUi = true, device = Devices.PIXEL)
@Composable
fun AppContainerPreview() {
    AppContainerView(
        modifier = Modifier.fillMaxWidth(),
        content = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(state = rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AppText(
                    text = R.string.signin_description,
                    color = AppColor.Gray
                )
            }
        }
    )
}