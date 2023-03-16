package com.example.androidlearn.di

import kotlinx.coroutines.delay

/**
 * @Author: luweiming
 * @Description:
 * @Date: Created in 10:05 2023/3/16
 */
class HomeRepo {
    suspend fun getList(): List<String> {
        delay(1_000)
        return listOf("1", "3", "5")
    }
}