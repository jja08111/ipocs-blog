package com.foundy.data.source

import com.foundy.data.api.UserApi
import com.foundy.domain.model.User
import javax.inject.Inject

class UserRemoteDataSourceImpl @Inject constructor(
    private val api: UserApi
) : UserRemoteDataSource {

    override suspend fun getAllUsers() : List<User> {
        return api.getAllUsers().users
    }
}