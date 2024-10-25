package com.git.admin.presenter.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import com.git.admin.R

@Composable
fun AppIcon(
    modifier: Modifier = Modifier,
    iconDrawable: Int,
    customColor: Color? = null
) {
    val painter: Painter = painterResource(id = iconDrawable)
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painter,
            contentDescription = "Custom Icon",
            contentScale = ContentScale.Fit,
            colorFilter = customColor?.let { ColorFilter.tint(it) }
        )
    }
}

@Preview(showSystemUi = true, device = Devices.PIXEL)
@Composable
fun AppIconPreview() {
    AppIcon(
        modifier = Modifier.fillMaxWidth(),
        iconDrawable =  R.drawable.ic_logo_extra_dark_phone,
    )
}