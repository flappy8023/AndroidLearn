package com.example.androidlearn.di

import android.util.Log
import kotlinx.coroutines.delay
import javax.inject.Inject

/**
 * @Author: luweiming
 * @Description:
 * @Date: Created in 10:05 2023/3/16
 */
class HomeRepo /*@Inject*/ constructor(val dao:MyDao) {
    suspend fun getList(): List<String> {
        delay(1_000)
        return listOf("1", "3", "5")
    }

    fun getNumFromDao() = dao.getNum()

    fun printA(){
        Log.d("DI_Test", "printA: ")
    }
}