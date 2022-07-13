package com.foundy.data.repository

import com.foundy.data.source.NoticeRemoteDataSource
import com.foundy.domain.model.Notice
import com.foundy.domain.repository.NoticeRepository
import javax.inject.Inject

class NoticeRepositoryImpl @Inject constructor(
    private val dataSource: NoticeRemoteDataSource
) : NoticeRepository {

    override suspend fun getAllNotices(): Result<List<Notice>> {
        return try {
            val notices = dataSource.getAllNotices()
            Result.success(notices)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun canWriteNotice(userId: Int): Result<Boolean> {
        return try {
            val response = dataSource.canWriteNotice(userId)
            if (response.success) {
                Result.success(true)
            } else {
                throw Exception(response.message)
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}