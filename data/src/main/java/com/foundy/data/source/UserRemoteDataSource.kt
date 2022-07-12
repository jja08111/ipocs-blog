package com.foundy.data.source

import com.foundy.domain.model.User

interface UserRemoteDataSource {
    suspend fun getAllUsers(): List<User>
}