package com.foundy.domain.repository

import com.foundy.domain.model.User

interface UserRepository {
    suspend fun getAllUsers(): Result<List<User>>
}