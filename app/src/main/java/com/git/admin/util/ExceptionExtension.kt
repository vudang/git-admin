package com.git.admin.util

import com.google.gson.Gson
import com.git.admin.data.model.base.APIError
import com.git.admin.data.model.base.Response
import retrofit2.HttpException

fun HttpException.mapToAPIError(): APIError {
    try {
        val errorBody = this.response()?.errorBody()?.string()
        val errorResponse = Gson().fromJson(errorBody, Response::class.java)
        val error = APIError(message = errorResponse.message ?: "Unknown error", code = errorResponse.responseCode)
        AppLogger.logE("com.offeright.android.data.model.base.APIError: $error")
        return error
    } catch (e: Exception) {
        val error = APIError(message = "Unknown error")
        AppLogger.logE("com.offeright.android.data.model.base.APIError: $e")
        return error
    }
}

fun Exception.mapToAPIError(): APIError {
    try {
        val message = this.message ?: "Unknown error"
        val error = APIError(message = message)
        AppLogger.logE("com.offeright.android.data.model.base.APIError: $error")
        return error
    } catch (e: Exception) {
        val error = APIError(message = "Unknown error")
        AppLogger.logE("com.offeright.android.data.model.base.APIError: $e")
        return error
    }
}