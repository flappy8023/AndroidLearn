package com.example.androidlearn.di

import android.app.Activity
import androidx.core.location.LocationRequestCompat.Quality
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier

@Module
@InstallIn(SingletonComponent::class)
class Module {
//    @Provides
//    fun provideDao() = MyDao()

    @Provides
    fun provideRepo(dao:MyDao) = HomeRepo(dao)

    @Provides
    @North
    fun provideApple():IFruit = Apple()

    @Provides
    fun apple():Apple=Apple()

    @Provides
    @South
    fun providePeach():IFruit = Peach()


}



@Qualifier
annotation class North
@Qualifier
annotation class South
