package com.foundy.domain.usecase

import com.foundy.domain.repository.NoticeRepository
import javax.inject.Inject

class GetAllNoticesUseCase @Inject constructor(
    private val repository: NoticeRepository
) {
    suspend operator fun invoke() = repository.getAllNotices()
}