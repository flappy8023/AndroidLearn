package com.example.androidlearn.multithread

import kotlin.concurrent.thread

/**
 * @Author: luweiming
 * @Description:
 * @Date: Created in 10:24 2023/2/22
 */
fun main() {
    val t1 = thread {
        while (true) {
            println("I am done 1")
        }
    }
//    t1.join()
}