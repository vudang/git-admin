package com.git.admin.presenter.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import com.git.admin.R
import com.git.admin.presenter.theme.AppColor
import com.git.admin.util.Dimens
import com.git.admin.util.ScreenUtils.isTablet

enum class AppButtonStyle {
    FILL, BORDER;

    @Composable
    fun getColor(): ButtonColors {
        return when (this) {
            FILL -> ButtonDefaults.buttonColors(
                containerColor = AppColor.GreenLight,
                contentColor = AppColor.White,
                disabledContainerColor = AppColor.GreenLight.copy(alpha = 0.5F),
                disabledContentColor = AppColor.White

            )
            BORDER -> ButtonDefaults.buttonColors(
                containerColor = AppColor.White,
                contentColor = AppColor.GreenLight,
                disabledContainerColor = AppColor.White,
                disabledContentColor = AppColor.GreenLight.copy(alpha = 0.5F)
            )
        }
    }

    @Composable
    fun getBorderColor(): Color {
        return when (this) {
            FILL -> Color.Transparent
            BORDER -> AppColor.GreenLight
        }
    }

    @Composable
    fun getTextColor(): Color {
        return when (this) {
            FILL -> AppColor.White
            BORDER -> AppColor.GreenLight
        }
    }
}

@Composable
fun AppButtonRegular(
    modifier: Modifier = Modifier,
    style: AppButtonStyle = AppButtonStyle.FILL,
    text: Int = R.string.empty,
    enable: Boolean = true,
    rawText: String? = null,
    customHeight: Dp? = null,
    customRadius: Dp? = null,
    customBgColor: Color? = null,
    customBorderColor: Color? = null,
    customTextColor: Color? = null,
    customTextSize: TextUnit? = null,
    onClick: () -> Unit
) {
    val tablet = isTablet()
    val height = customHeight ?: if(tablet) Dimens.dp57 else Dimens.dp46
    val radius = customRadius ?: Dimens.dp15
    val textSize = customTextSize ?: if(tablet) Dimens.sp16 else Dimens.sp15
    val bgColor = if (customBgColor != null) ButtonDefaults.buttonColors(
        containerColor = customBgColor
    ) else null
    val borderColor = customBorderColor ?: style.getBorderColor()
    Button(
        onClick = onClick,
        colors = bgColor ?: style.getColor(),
        enabled = enable,
        border = BorderStroke(
            width = Dimens.dp1,
            color = borderColor
        ),
        shape = RoundedCornerShape(radius),
        modifier = modifier
            .height(height)
    ) {
        AppText(
            text = text,
            rawText = rawText,
            size = textSize,
            color = customTextColor ?: style.getTextColor(),
            weight = FontWeight.Black,
        )
    }
}

@Preview(showSystemUi = true, device = Devices.PIXEL_TABLET)
@Composable
fun AppButtonRegularPreview() {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly, verticalAlignment = Alignment.CenterVertically) {
        AppButtonRegular(
            style = AppButtonStyle.FILL,
            text = R.string.signin,
            onClick = {},
            modifier = Modifier.weight(1f)
        )
    }
}