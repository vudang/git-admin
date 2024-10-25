package com.git.admin.presenter.component

import android.annotation.SuppressLint
import androidx.compose.foundation.border
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Stable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.Dp
import com.git.admin.presenter.theme.AppColor
import com.git.admin.util.Dimens

@SuppressLint("ModifierFactoryUnreferencedReceiver")
@Stable
fun Modifier.appBorder(width: Dp = Dimens.dp1,
                       radius: Dp = Dimens.dp15,
                       color: Color = AppColor.Light) =
    border(width, SolidColor(color), RoundedCornerShape(radius))

fun Modifier.appDottedBorder(
    color: Color = AppColor.Light,
    width: Dp = Dimens.dp4,
    radius: Dp = Dimens.dp15,
    dotSize: Dp = Dimens.dp4,
    dotSpacing: Dp = Dimens.dp4
) = this.drawBehind {
    val pathEffect = PathEffect.dashPathEffect(
        floatArrayOf(dotSize.toPx(), dotSpacing.toPx())
    )

    drawRoundRect(
        color = color,
        size = Size(size.width, size.height),
        style = Stroke(
            width = width.toPx(),
            pathEffect = pathEffect
        ),
        cornerRadius = CornerRadius(radius.toPx())
    )
}