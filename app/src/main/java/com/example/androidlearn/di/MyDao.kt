package com.example.androidlearn.di

import javax.inject.Inject

/**
 * @Author flappy8023
 * @Description
 * @Date 2024年10月18日 10:23
 **/
class MyDao @Inject constructor() {
    fun getNum() = 10
}