package com.foundy.data.di

import com.foundy.data.repository.NoticeRepositoryImpl
import com.foundy.data.repository.UserRepositoryImpl
import com.foundy.data.source.NoticeRemoteDataSource
import com.foundy.data.source.UserRemoteDataSource
import com.foundy.domain.repository.NoticeRepository
import com.foundy.domain.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun provideUserRepository(dataSource: UserRemoteDataSource) : UserRepository {
        return UserRepositoryImpl(dataSource)
    }

    @Singleton
    @Provides
    fun provideNoticeRepository(dataSource: NoticeRemoteDataSource) : NoticeRepository {
        return NoticeRepositoryImpl(dataSource)
    }
}