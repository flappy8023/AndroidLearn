package com.example.javatest.multithread;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: luweiming
 * @Description:
 * @Date: Created in 14:28 2022/11/9
 */
public class AtomicTest {
    static AtomicInteger integer = new AtomicInteger(0);
    static volatile int inta = 0;

    public static void main(String[] args) {
        Thread t1 = new Thread(new MyRun());
        Thread t2 = new Thread(new MyRun());
        t1.start();
        t2.start();

    }

    static class MyRun implements Runnable {
        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
//                System.out.println(Thread.currentThread().getName() + ":" + integer.getAndIncrement());
                System.out.println(Thread.currentThread().getName() + ":" + inta++);
            }
        }
    }
}
