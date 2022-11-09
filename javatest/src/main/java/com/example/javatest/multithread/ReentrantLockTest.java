package com.example.javatest.multithread;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: luweiming
 * @Description:
 * @Date: Created in 11:09 2022/11/9
 */
public class ReentrantLockTest {
    private static ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) {
        Thread thread1 = new Thread(new MyRun());
        Thread thread2 = new Thread(new MyRun());
        thread1.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread2.start();
    }

    static class MyRun implements Runnable {
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " begin request lock");
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + " got the lock");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } finally {
                lock.unlock();
                System.out.println(Thread.currentThread().getName() + " release the lock");
            }
        }
    }
}
