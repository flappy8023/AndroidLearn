package com.example.androidlearn.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * @Author flappy8023
 * @Description
 * @Date 2024年10月18日 16:15
 **/
@Module
@InstallIn(SingletonComponent::class)
abstract class BindsModule {
    @Binds
    abstract fun bindFruit(apple: Apple): IFruit
}