package com.git.admin.util

import com.google.gson.Gson
import com.git.admin.data.model.base.APIError
import com.git.admin.data.model.base.ErrorCode
import com.git.admin.data.model.base.Response
import retrofit2.HttpException

fun HttpException.mapToAPIError(): APIError {
    try {
        val errorBody = this.response()?.errorBody()?.string()
        val errorResponse = Gson().fromJson(errorBody, Response::class.java)
        val error = APIError(message = errorResponse.message ?: "Unknown error", code = errorResponse.responseCode)
        return error
    } catch (e: Exception) {
        val error = APIError(message = "Unknown error")
        return error
    }
}

fun Exception.mapToAPIError(): APIError {
    try {
        val message = this.message ?: "Unknown error"
        val error = APIError(message = message)
        return error
    } catch (e: Exception) {
        val error = APIError(message = "Unknown error")
        return error
    }
}

fun Throwable.handleAPIError(): APIError {
    return when (this) {
        is HttpException -> this.mapToAPIError()
        is Exception -> this.mapToAPIError()
        else -> APIError(message = this.message ?: "Unknown error", code = ErrorCode.UNKNOWN_ERROR)
    }
}