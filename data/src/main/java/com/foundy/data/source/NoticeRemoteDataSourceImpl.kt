package com.foundy.data.source

import com.foundy.data.api.NoticeApi
import com.foundy.data.api.NoticeWritePermissionEntity
import com.foundy.domain.model.Notice
import javax.inject.Inject

class NoticeRemoteDataSourceImpl @Inject constructor(
    private val api: NoticeApi
): NoticeRemoteDataSource {

    override suspend fun getAllNotices(): List<Notice> {
        return api.getAllNotices().notices
    }

    override suspend fun canWriteNotice(userId: Int): NoticeWritePermissionEntity {
        return api.canWriteNotice(userId = userId)
    }
}