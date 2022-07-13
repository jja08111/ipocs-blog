package com.foundy.domain.usecase.user

import com.foundy.domain.repository.UserRepository
import javax.inject.Inject

class GetAllUsersUseCase @Inject constructor(
    private val repository: UserRepository
) {
    suspend operator fun invoke() = repository.getAllUsers()
}