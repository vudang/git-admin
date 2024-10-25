package com.git.admin.presenter.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter

@Composable
fun AppImageLoader(
    imageUrl: String,
    placeHolder: Int? = null
) {
    val painter = rememberAsyncImagePainter(
        model = imageUrl,
        placeholder = placeHolder?.let { painterResource(it) },
    )
    val painterState = painter.state

    Image(
        painter = painter,
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .fillMaxWidth()
    )

    if (painterState is AsyncImagePainter.State.Loading) {
        AppLoadingIndicator()
    }
}