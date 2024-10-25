package com.git.admin.data.model.base

import com.google.gson.annotations.SerializedName

data class Response<T>(
    @SerializedName("message")
    val message: String? = null,

    @SerializedName("errors")
    val errors: List<String>? = null,

    @SerializedName("response_code")
    val responseCode: String? = null,

    @SerializedName("data")
    val data: T? = null
)