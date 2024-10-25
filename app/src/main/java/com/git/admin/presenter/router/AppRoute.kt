package com.git.admin.presenter.router

enum class AppRoute {
    ROOT,
    USER_DASHBOARD,
    USER_DETAIL;

    fun getName(): String {
        return when(this) {
            ROOT -> "root"
            USER_DASHBOARD -> "home"
            USER_DETAIL -> "user_detail"
        }
    }
}