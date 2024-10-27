package com.git.admin.presenter.root
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.git.admin.domain.model.UiState
import com.git.admin.presenter.router.AppRoute
import com.git.admin.presenter.router.AppRouter
import com.git.admin.presenter.theme.AppColor
import com.git.admin.presenter.theme.AppTheme

/** Created by github.com/im-o on 12/12/2022. */

@Composable
fun MainScreen (
    windowSize: WindowWidthSizeClass,
    router: AppRouter,
    viewModel: RootViewModel = hiltViewModel()
) {
    // Make app full cover the device status bar
    val systemUiController = rememberSystemUiController()
    systemUiController.setSystemBarsColor(
        color = AppColor.Clear,
    )

    val uiState by remember { viewModel.uiState }.collectAsState()

    fun navigateToHome() {
        router.push(AppRoute.HOME)
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        when (uiState) {
            is UiState.Success -> {
                navigateToHome()
            }
            else -> {}
        }
    }
}

@Preview(showBackground = true, showSystemUi = true, device = Devices.PIXEL_4)
@Composable
fun DefaultPreview() {
    AppTheme {
        MainScreen(
            windowSize = WindowWidthSizeClass.Compact,
            router = AppRouter(rememberNavController())
        )
    }
}