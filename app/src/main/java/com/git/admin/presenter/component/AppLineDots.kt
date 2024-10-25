package com.git.admin.presenter.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import com.git.admin.presenter.theme.AppColor
import com.git.admin.util.Dimens

@Composable
fun AppLineWithDots(
    modifier: Modifier = Modifier
) {
    Canvas(modifier = modifier
        .fillMaxWidth()
        .height(Dimens.dp10)
        .background(AppColor.Clear)) {

        val canvasWidth = size.width
        val canvasHeight = size.height

        val dotRadius = Dimens.dp1.toPx()
        val spaceBetweenDots = Dimens.dp4.toPx()

        // Draw the dots
        var currentX = 0f
        while (currentX < canvasWidth) {
            drawCircle(
                color = AppColor.Light,
                radius = dotRadius,
                center = androidx.compose.ui.geometry.Offset(currentX, canvasHeight / 2)
            )
            currentX += spaceBetweenDots
        }
    }
}

@Preview(showSystemUi = true, device = Devices.PIXEL)
@Composable
fun AppLineDotsPreview() {
    AppLineWithDots(modifier = Modifier.padding())
}