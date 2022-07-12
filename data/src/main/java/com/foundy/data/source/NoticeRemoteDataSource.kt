package com.foundy.data.source

import com.foundy.domain.model.Notice

interface NoticeRemoteDataSource {
    suspend fun getAllNotices(): List<Notice>
}