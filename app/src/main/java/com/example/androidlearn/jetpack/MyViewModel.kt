package com.example.androidlearn.jetpack

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * description:
 * date: 2022/3/2 17:10
 * author: luweiming
 * version: 1.0
 */
class MyViewModel:ViewModel() {
    val text:LiveData<String> = MutableLiveData<String>().apply { value = "1000" }
}