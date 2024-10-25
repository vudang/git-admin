package com.git.admin.presenter.component.skeleton

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import com.git.admin.util.Dimens
import com.git.admin.util.ScreenUtils.isTablet

@Composable
fun AppSkeletonItem(modifier: Modifier = Modifier) {
    val mainHeight = if (isTablet()) Dimens.dp50 else Dimens.dp20
    val secondaryHeight = if (isTablet()) Dimens.dp30 else Dimens.dp10
    val spacing = if (isTablet()) Dimens.dp10 else Dimens.dp4
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(spacing)
    ) {
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(mainHeight)
                .clip(RoundedCornerShape(Dimens.dp8))
                .appSkeleton()
        )
        Spacer(
            modifier = Modifier
                .fillMaxWidth(0.7f)
                .height(secondaryHeight)
                .clip(RoundedCornerShape(Dimens.dp8))
                .appSkeleton()
        )
    }
}

@Preview(showSystemUi = true, device = Devices.PIXEL_TABLET)
@Composable
private fun AppSkeletonPreview() {
    AppSkeletonItem()
}
