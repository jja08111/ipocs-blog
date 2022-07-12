package com.foundy.data.di

import com.foundy.data.api.UserApi
import com.foundy.data.source.UserRemoteDataSource
import com.foundy.data.source.UserRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RemoteDataModule {

    @Provides
    @Singleton
    fun provideUserRemoteDataSource(api: UserApi) : UserRemoteDataSource {
        return UserRemoteDataSourceImpl(api)
    }
}