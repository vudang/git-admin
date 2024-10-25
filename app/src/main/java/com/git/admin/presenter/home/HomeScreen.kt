package com.git.admin.presenter.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.git.admin.domain.model.User
import com.git.admin.presenter.component.AppContainerView
import com.git.admin.presenter.router.AppRouter
import com.git.admin.util.Dimens

@Composable
fun HomeScreen(
    windowSize: WindowWidthSizeClass,
    router: AppRouter
) {
    var segmentSelected by remember { mutableIntStateOf(0) }

    AppContainerView(
        modifier = Modifier.padding(),
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = Dimens.dp20)
                .padding(top = Dimens.dp20)
                .verticalScroll(state = rememberScrollState())
        ) {
        }
    }
}

