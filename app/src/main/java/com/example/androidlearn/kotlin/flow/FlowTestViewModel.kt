package com.example.androidlearn.kotlin.flow

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow

/**
 * @Author: luweiming
 * @Description:
 * @Date: Created in 16:14 2022/10/27
 */
class FlowTestViewModel : ViewModel() {
    val timer = flow {
        var num = 0
        while (true) {
            emit(num++)
            delay(1000)
        }
    }
    val timer1 = flow {
        var num = 0
        while (true){
            emit(num++)
            delay(1000)
        }
    }
}