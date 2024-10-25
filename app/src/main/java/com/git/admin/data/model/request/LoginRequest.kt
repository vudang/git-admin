package com.git.admin.data.model.request

import com.google.gson.annotations.SerializedName
import com.git.admin.util.isValidEmail

data class LoginRequest(
    @SerializedName("email")
    var email: String? = null,
    @SerializedName("password")
    var password: String? = null
) {
    fun isValid(): Boolean {
        return (email ?: "").isValidEmail() &&
                (password ?: "").isNotEmpty()
    }
}

fun LoginRequest.setEmail(email: String) {
    this.email = email
}

fun LoginRequest.setPassword(password: String) {
    this.password = password
}