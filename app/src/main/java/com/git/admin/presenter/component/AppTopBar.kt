package com.git.admin.presenter.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import com.git.admin.R
import com.git.admin.presenter.theme.AppColor
import com.git.admin.util.Dimens

@Composable
fun AppTopBar(
    modifier: Modifier,
) {
    Row(
        modifier = modifier.padding(vertical = Dimens.dp16),
        horizontalArrangement = Arrangement.spacedBy(Dimens.dp0),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AppText(
            text = R.string.app_name,
            color = AppColor.Black,
            size = Dimens.sp18,
            weight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.weight(1f))
    }
}

@Preview(showSystemUi = true, device = Devices.PIXEL)
@Composable
fun AppTopBarPhonePreview() {
    AppTopBar(
        modifier = Modifier
            .fillMaxWidth()
    )
}