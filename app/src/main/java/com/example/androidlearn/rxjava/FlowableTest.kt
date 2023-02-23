package com.example.androidlearn.rxjava

import io.reactivex.rxjava3.core.BackpressureStrategy
import io.reactivex.rxjava3.core.Flowable

/**
 * @Author: luweiming
 * @Description:
 * @Date: Created in 9:57 2023/2/21
 */
class FlowableTest {}

fun main() {
    Flowable.create<Int>({ emitter -> emitter.onNext(10) }, BackpressureStrategy.BUFFER)
        .map { it -> it + 1 }
        .subscribe {
            println(it)
        }
}