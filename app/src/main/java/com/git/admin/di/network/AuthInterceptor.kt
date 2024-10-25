package com.git.admin.di.network

import okhttp3.Interceptor
import okhttp3.Response

/** Created by Tony on 12/16/2024. */

class AuthInterceptor(
    private val token: () -> String?
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val request = originalRequest
            .newBuilder()
            .url(originalRequest.url)

        token()?.let {
            request.addHeader("Authorization", "Bearer $it")
        }

        return chain.proceed(request.build())
    }
}