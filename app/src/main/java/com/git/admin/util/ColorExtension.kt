package com.git.admin.util

import androidx.compose.ui.graphics.Color

fun Color.alpha(alpha: Float): Color {
    return this.copy(alpha = alpha)
}