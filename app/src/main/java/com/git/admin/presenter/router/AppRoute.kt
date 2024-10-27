package com.git.admin.presenter.router

enum class AppRoute {
    ROOT,
    HOME,
    USER_DETAIL;

    fun getName(): String {
        return when(this) {
            ROOT -> "root"
            HOME -> "home"
            USER_DETAIL -> "user_detail"
        }
    }
}