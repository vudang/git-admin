package com.git.admin.data.model.base

data class APIError(
    val message: String,
    val code: String? = null
)
