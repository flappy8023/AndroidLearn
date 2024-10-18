package com.example.androidlearn.di

import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * @Author flappy8023
 * @Description
 * @Date 2024年10月18日 11:27
 **/
@InstallIn(SingletonComponent::class)
@EntryPoint
interface EntryPointTest {
    fun getRepo():HomeRepo
}