package com.git.admin.presenter.component.skeleton

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import com.git.admin.presenter.component.appBorder
import com.git.admin.util.Dimens

@Composable
fun AppSkeletonList(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(Dimens.dp10)
    ) {
        for (i in 0..10) {
            AppSkeletonItem(
                modifier = Modifier
                    .fillMaxWidth()
                    .appBorder()
                    .padding(Dimens.dp10)
            )
        }
    }
}

@Preview(showSystemUi = true, device = Devices.PIXEL_TABLET)
@Composable
private fun AppSkeletonListPreview() {
    AppSkeletonList()
}
