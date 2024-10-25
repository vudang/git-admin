package com.git.admin.util

import android.content.Context
import android.content.res.Configuration
import android.util.DisplayMetrics
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp

fun Configuration.isTablet(context: Context, thresholdDp: Dp = Dimens.dp600): Boolean {
    val displayMetrics: DisplayMetrics = context.resources.displayMetrics
    val screenWidthDp = this.screenWidthDp
    val thresholdPx = with(displayMetrics) { thresholdDp.toPx(displayMetrics = displayMetrics) }
    val screenWidthPx = screenWidthDp * displayMetrics.density

    return screenWidthPx >= thresholdPx
}

// Extension function to convert Dp to Pixels
fun Dp.toPx(displayMetrics: DisplayMetrics): Float {
    return this.value * displayMetrics.density
}

object ScreenUtils {
    @Composable
    fun isTablet(): Boolean {
        val context = LocalContext.current
        val configuration = LocalConfiguration.current
        val isTablet = configuration.isTablet(context)

        return isTablet
    }
}