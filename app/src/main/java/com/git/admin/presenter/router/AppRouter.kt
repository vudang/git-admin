package com.git.admin.presenter.router

import androidx.navigation.NavController

class AppRouter(
    val navController: NavController
) {
    fun push(to: AppRoute) {
        navController.navigate(route = to.getName())
    }

    fun pop() {
        navController.popBackStack()
    }

    fun popToRoot() {
        val id = navController.graph.startDestinationId
        navController.popBackStack(destinationId = id, inclusive = false)
    }

    fun popTo(page: AppRoute) {
        val id = navController.graph.findNode(page.getName())?.id
        navController.popBackStack(destinationId = id!!, inclusive = false)
    }
}