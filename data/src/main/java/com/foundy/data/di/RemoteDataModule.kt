package com.foundy.data.di

import com.foundy.data.api.NoticeApi
import com.foundy.data.api.UserApi
import com.foundy.data.source.NoticeRemoteDataSource
import com.foundy.data.source.NoticeRemoteDataSourceImpl
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

    @Provides
    @Singleton
    fun provideNoticeRemoteDataSource(api: NoticeApi) : NoticeRemoteDataSource {
        return NoticeRemoteDataSourceImpl(api)
    }
}