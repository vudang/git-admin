package com.git.admin.presenter.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import com.git.admin.R
import com.git.admin.presenter.theme.AppColor
import com.git.admin.util.Dimens

@Composable
fun AppButtonIcon(
    modifier: Modifier = Modifier,
    padding: Dp = Dimens.dp10,
    iconDrawable: Int,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .background(color = AppColor.Clear, shape = RoundedCornerShape(Dimens.dp4))
            .clickable(onClick = onClick)
            .padding(padding)
    ) {
        AppIcon(iconDrawable = iconDrawable)
    }
}

@Preview(showSystemUi = true, device = Devices.PIXEL)
@Composable
fun AppButtonIconPreview() {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly, verticalAlignment = Alignment.CenterVertically) {
        AppButtonIcon(
            iconDrawable = R.drawable.ic_notify,
            modifier = Modifier.padding()
        ) {

        }
    }
}