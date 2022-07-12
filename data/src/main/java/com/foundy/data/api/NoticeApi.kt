package com.foundy.data.api

import com.foundy.data.model.NoticesEntity
import retrofit2.http.GET

interface NoticeApi {
    @GET("notices")
    suspend fun getAllNotices(): NoticesEntity
}