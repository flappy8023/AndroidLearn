package com.example.androidlearn.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * @Author: luweiming
 * @Description:
 * @Date: Created in 10:04 2023/3/16
 */
val repoModule = module {
    single { HomeRepo() }
}
val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
}
val myModule = listOf(repoModule, viewModelModule)