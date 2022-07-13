package com.foundy.data.api

import com.foundy.data.model.NoticesEntity
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface NoticeApi {
    @GET("notices")
    suspend fun getAllNotices(): NoticesEntity

    @FormUrlEncoded
    @POST("notices")
    suspend fun canWriteNotice(
        @Field("title") title: String = "",
        @Field("content") content: String = "",
        @Field("userId") userId: Int
    ): NoticeWritePermissionEntity
}