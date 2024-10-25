package com.git.admin.util

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import com.git.admin.R

object AppFont {
    val fontFamily = FontFamily(
        Font(R.font.inter_thin, FontWeight.Thin, style = FontStyle.Normal),
        Font(R.font.inter_extra_light, FontWeight.ExtraLight, style = FontStyle.Normal),
        Font(R.font.inter_light, FontWeight.Light, style = FontStyle.Normal),
        Font(R.font.inter_regular, FontWeight.Normal, style = FontStyle.Normal),
        Font(R.font.inter_medium, FontWeight.Medium, style = FontStyle.Normal),
        Font(R.font.inter_semi_bold, FontWeight.SemiBold, style = FontStyle.Normal),
        Font(R.font.inter_bold, FontWeight.Bold, style = FontStyle.Normal),
        Font(R.font.inter_extra_bold, FontWeight.ExtraBold, style = FontStyle.Normal),
        Font(R.font.inter_black, FontWeight.Black, style = FontStyle.Normal)
    )
}