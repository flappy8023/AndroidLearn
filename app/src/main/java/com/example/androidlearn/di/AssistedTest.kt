package com.example.androidlearn.di

import android.util.Log
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import javax.inject.Inject

/**
 * @Author flappy8023
 * @Description
 * @Date 2024年10月18日 11:20
 **/
class AssistedTest @AssistedInject constructor(@Assisted val name:String) {
    @AssistedFactory
    interface TestFactory{
        fun createTest(name:String):AssistedTest
    }

    fun printName() {
        Log.d("DI_Test", "printName: $name")
    }
}


