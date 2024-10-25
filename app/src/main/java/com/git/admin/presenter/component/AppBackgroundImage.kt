package com.git.admin.presenter.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource


@Composable
fun Modifier.backgroundWithImage(imageRes: Int, contentScale: ContentScale = ContentScale.Crop): Modifier {
    return this.then(
        Modifier.paint(
            painter = painterResource(id = imageRes),
            contentScale = contentScale
        )
    )
}