package com.example.javatest.multithread;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * @Author: luweiming
 * @Description:
 * @Date: Created in 15:32 2022/11/9
 */
public class BlockQueueTest {
    private static ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<>(10, true);

    public static void main(String[] args) {
//        ThreadGroup group1 = new ThreadGroup("producer");
//        ThreadGroup group2 = new ThreadGroup("consumer");
        new Thread(new Producer(), "producer").start();
        new Thread(new Consumer(), "consumer").start();
    }

    static class Producer implements Runnable {
        @Override
        public void run() {
            try {
                for (int i = 0; i < 20; i++) {
                    Thread.sleep(500);
                    queue.put(10);
                    System.out.println(Thread.currentThread().getName() + ":put");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class Consumer implements Runnable {
        @Override
        public void run() {
            try {
                for (int i = 0; i < 20; i++) {
                    Thread.sleep(1000);
                    queue.take();
                    System.out.println(Thread.currentThread().getName() + ":take");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
