package com.git.admin.data.datasource.remote

import com.git.admin.data.model.base.Response
import com.git.admin.data.model.response.UserDetailEntity
import com.git.admin.data.model.response.UserEntity
import retrofit2.http.GET
import retrofit2.http.Query

/** Created by Tony on 10/1/2024. */

interface NetworkService {
    @GET("/users/profile")
    suspend fun getUserDetail(): Response<UserDetailEntity>

    @GET("/users")
    suspend fun getListUser(
        @Query("per_page") size: Int,
        @Query("since") page: Int
    ): List<UserEntity>
}