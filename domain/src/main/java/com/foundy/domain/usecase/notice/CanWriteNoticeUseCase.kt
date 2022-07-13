package com.foundy.domain.usecase.notice

import com.foundy.domain.repository.NoticeRepository
import javax.inject.Inject

class CanWriteNoticeUseCase @Inject constructor(
    private val repository: NoticeRepository
) {
    suspend operator fun invoke(userId: Int) = repository.canWriteNotice(userId)
}