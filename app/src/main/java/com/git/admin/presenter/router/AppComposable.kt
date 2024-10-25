package com.git.admin.presenter.router

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

fun NavGraphBuilder.appComposable(
    route: AppRoute,
    content: @Composable AnimatedContentScope.(NavBackStackEntry) -> Unit
) {
    // TODO: - FIXME - Fix animation
    composable(
        route = route.getName(),
//        enterTransition = {
//            slideInHorizontally(initialOffsetX = { 500 })
//        },
//        exitTransition = {
//            slideOutHorizontally(targetOffsetX = { 500 })
//        },
        content = content
    )
}