package com.example.javatest.multithread;

/**
 * @Author: luweiming
 * @Description:
 * @Date: Created in 10:40 2022/11/9
 */
public class ThreadT {
    public static void main(String[] args) {
        Thread thread = new Thread(new MyRunnable());
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            System.out.println("interrupted exception");
            e.printStackTrace();
        }
        Thread thread1 = new Thread(new MyRunnable());
        thread1.start();
    }

    static class MyRunnable implements Runnable {
        @Override
        public void run() {
            int i = 0;
            while (i < 10) {
                i++;
                try {
                    Thread.sleep(500);
                    System.out.println("Thread:" + Thread.currentThread().getName() + ", " + i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
