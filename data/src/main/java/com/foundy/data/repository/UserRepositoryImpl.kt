package com.foundy.data.repository

import com.foundy.data.source.UserRemoteDataSource
import com.foundy.domain.model.User
import com.foundy.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val dataSource: UserRemoteDataSource
): UserRepository {

    override suspend fun getAllUsers(): Result<List<User>> {
        return try {
            val users = dataSource.getAllUsers()
            Result.success(users)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}