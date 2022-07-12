package com.foundy.data

import com.foundy.data.di.NetworkModule
import kotlinx.coroutines.runBlocking
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun user_api() {
        val api = with(NetworkModule()) {
            provideUserApiService(provideRetrofit(provideHttpClient()))
        }
        runBlocking {
            val result = api.getAllUsers()
            result.users.forEach {
                println(it)
            }
            assertTrue(result.users.isNotEmpty())
        }
    }

    @Test
    fun notice_api() {
        val api = with(NetworkModule()) {
            provideNoticeApiService(provideRetrofit(provideHttpClient()))
        }
        runBlocking {
            val result = api.getAllNotices()
            result.notices.forEach {
                println(it)
            }
            assertTrue(result.notices.isNotEmpty())
        }
    }
}