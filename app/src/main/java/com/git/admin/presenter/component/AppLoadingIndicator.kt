package com.git.admin.presenter.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import com.git.admin.presenter.theme.AppColor
import com.git.admin.util.Dimens

@Composable
fun AppLoadingIndicator(
    modifier: Modifier = Modifier,
    touchable: Boolean = true,
    bgAlpha: Float = 0.5f
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(AppColor.Dark.copy(alpha = bgAlpha))
            .clickable(enabled = touchable) { },
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            color = AppColor.GreenDark,
            strokeWidth = Dimens.dp4,
            trackColor = AppColor.GreenExtraLight
        )
    }
}

@Preview(showSystemUi = true, device = Devices.PIXEL)
@Composable
private fun AppLoadingIndicatorPreview() {
    AppLoadingIndicator()
}
