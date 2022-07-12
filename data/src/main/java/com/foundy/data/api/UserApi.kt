package com.foundy.data.api

import com.foundy.data.model.UsersEntity
import retrofit2.http.GET

interface UserApi {
    @GET("users")
    suspend fun getAllUsers(): UsersEntity
}