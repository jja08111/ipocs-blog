package com.foundy.data.source

import com.foundy.data.api.NoticeWritePermissionEntity
import com.foundy.domain.model.Notice

interface NoticeRemoteDataSource {
    suspend fun getAllNotices(): List<Notice>
    suspend fun canWriteNotice(userId: Int): NoticeWritePermissionEntity
}