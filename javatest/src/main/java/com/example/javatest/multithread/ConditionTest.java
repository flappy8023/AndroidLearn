package com.example.javatest.multithread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: luweiming
 * @Description:
 * @Date: Created in 16:31 2022/11/9
 */
public class ConditionTest {
    private static Lock lock = new ReentrantLock();
    private static Condition condition = lock.newCondition();

    public static void main(String[] args) {
        new Thread(new MyRun()).start();
        new Thread(new MyRun1()).start();
    }

    static class MyRun implements Runnable {
        @Override
        public void run() {
            lock.lock();
            try {
                for (int i = 0; i < 20; i++) {
                    try {
                        Thread.sleep(500);
                        System.out.println(Thread.currentThread().getName() + ":" + i);

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (i == 5) {
                        try {
                            condition.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            } finally {
                lock.unlock();
            }
        }

    }

    static class MyRun1 implements Runnable {
        @Override
        public void run() {
            lock.lock();
            try {


                for (int i = 0; i < 20; i++) {
                    try {
                        Thread.sleep(500);
                        System.out.println(Thread.currentThread().getName() + ":" + i);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (i == 19) {
                        condition.signal();
                    }
                }
            } finally {
                lock.unlock();
            }
        }
    }
}
