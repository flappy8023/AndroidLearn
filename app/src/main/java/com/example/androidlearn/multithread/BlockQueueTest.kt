package com.example.androidlearn.multithread

import java.util.concurrent.LinkedBlockingQueue
import java.util.concurrent.TimeUnit
import kotlin.concurrent.thread

/**
 * @Author: luweiming
 * @Description:
 * @Date: Created in 10:05 2023/2/22
 */
fun main() {
    val blockQueue = LinkedBlockingQueue<Int>(10)
    thread(name = "Producer") {
        while (true) {
            //offer不阻塞
            val offerResult = blockQueue.offer(1)
            println(Thread.currentThread().name + offerResult)
        }
    }
    thread(name = "Consumer") {
        while (true) {
            //poll不阻塞
            val pollResult = blockQueue.poll(10, TimeUnit.MILLISECONDS)
            println(Thread.currentThread().name + pollResult)

        }
    }
}
