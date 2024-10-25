package com.git.admin.presenter.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import com.git.admin.R
import com.git.admin.presenter.theme.AppColor
import com.git.admin.util.Dimens
import com.git.admin.util.ScreenUtils.isTablet
import com.git.admin.util.alpha


@Composable
fun AppButtonText(
    modifier: Modifier = Modifier,
    title: Int,
    enabled: Boolean = true,
    titleColor: Color = AppColor.White,
    titleSize: TextUnit? = null,
    textWeight: FontWeight = FontWeight.SemiBold,
    onClick: () -> Unit
) {
    val defaultSize = if (isTablet()) Dimens.sp13 else Dimens.sp14
    val textSize = titleSize ?: defaultSize
    val color = if (enabled) titleColor else titleColor.alpha(0.5f)
    Button(
        onClick = {
            if (enabled) {
                onClick()
            }
      },
        modifier = modifier
            .wrapContentHeight(unbounded = true)
            .wrapContentWidth(unbounded = true),
        colors = ButtonDefaults.buttonColors(
            containerColor = AppColor.Clear
        )
    ) {
        AppText(
            text = title,
            size = textSize,
            color = color,
            weight = textWeight,
        )
    }
}

@Preview(showSystemUi = true, device = Devices.PIXEL)
@Composable
fun AppButtonTextPreview() {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly, verticalAlignment = Alignment.CenterVertically) {
        AppButtonText(
            title = R.string.signin,
            titleColor = AppColor.Dark,
            titleSize = Dimens.sp18,
            onClick = {},
            modifier = Modifier.weight(1f)
        )
    }
}