package com.example.androidlearn.kotlin.flow

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * @Author: luweiming
 * @Description:
 * @Date: Created in 16:14 2022/10/27
 */
class FlowTestViewModel : ViewModel() {
    companion object {
        private const val TAG = "wwwwwwwwwwww"
    }

    val timer = flow {
        var num = 0
        while (true) {
            emit(num++)
            delay(1000)
        }
    }
    val timer1 = flow {
        var num = 0
        while (true) {
            emit(num++)
            delay(1000)
        }
    }

    //冷流，不同的消费者获取独立的数据
    val timer2: Flow<Int> = flow {
        for (i in 0..10) {
            emit(i)
            delay(500)
        }

    }
}