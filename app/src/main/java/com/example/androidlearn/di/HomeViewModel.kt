package com.example.androidlearn.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

/**
 * @Author: luweiming
 * @Description:
 * @Date: Created in 10:06 2023/3/16
 */
class HomeViewModel(val repo: HomeRepo) : ViewModel() {
    private val _flow = MutableStateFlow<List<String>>(listOf())
    val flow: StateFlow<List<String>> = _flow

    fun getData() {
        viewModelScope.launch {
            val result = repo.getList()
            _flow.emit(result)
        }
    }
}