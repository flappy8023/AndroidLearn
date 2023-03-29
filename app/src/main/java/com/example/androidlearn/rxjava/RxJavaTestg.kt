package com.example.androidlearn.rxjava

import io.reactivex.rxjava3.core.Observable

/**
 * @Author: luweiming
 * @Description:
 * @Date: Created in 22:12 2023/3/19
 */
class RxJavaTestg {

}

fun main() {
    Observable.create<Int> {
        it.onNext(10)
    }.map {
        it.toString()
    }.subscribe {
        println(it)
    }
}