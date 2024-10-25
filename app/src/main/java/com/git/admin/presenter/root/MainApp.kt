package com.git.admin.presenter.root

import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.git.admin.presenter.router.AppRoute
import com.git.admin.presenter.router.AppRouter
import com.git.admin.presenter.router.appComposable
import com.git.admin.presenter.home.HomeScreen
import com.git.admin.presenter.user_detail.UserDetailScreen

/** Created by github.com/im-o on 10/28/2023. */

@Composable
fun MainApp(
    windowSize: WindowWidthSizeClass
) {
    val navController = rememberNavController()
    val appRouter = AppRouter(navController = navController)
    NavHost(navController = navController, startDestination = AppRoute.ROOT.getName()) {
        appComposable(AppRoute.ROOT) {
            MainScreen(
                windowSize = windowSize,
                router = appRouter
            )
        }
        appComposable(AppRoute.USER_DASHBOARD) {
            HomeScreen(
                windowSize = windowSize,
                router = appRouter
            )
        }
        appComposable(AppRoute.USER_DETAIL) {
            UserDetailScreen(
                windowSize = windowSize,
                router = appRouter
            )
        }
    }
}