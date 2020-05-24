package com.example.thread.thread2.deadthread;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * author : fenzili
 * e-mail : 291924028@qq.com
 * date   : 2020/5/16 21:32
 * pkn    : com.example.thread.thread2
 * desc   : 演示尝试拿锁解决死锁
 */
public class TryLock {

    private static Lock No13 = new ReentrantLock();
    private static Lock No14 = new ReentrantLock();

    private static void firstToSecond() throws InterruptedException {
        String threadName = Thread.currentThread().getName();
        Random random = new Random();
        while (true) {
            if (No13.tryLock()) {
                System.out.println(threadName + " get 13");
                try {
                    if (No14.tryLock()) {
                        try {
                            System.out.println(threadName + " get 14");
                            System.out.println("fisrtToSecond do work------------");
                            break;
                        } finally {
                            No14.unlock();
                        }
                    }
                } finally {
                    No13.unlock();
                }
            }
            Thread.sleep(random.nextInt(3));
        }
    }

    private static void secondToFirst() throws InterruptedException {
        String threadName = Thread.currentThread().getName();
        Random random = new Random();
        while (true) {
            if (No14.tryLock()) {
                System.out.println(threadName + " get 14");
                try {
                    if (No13.tryLock()) {
                        try {
                            System.out.println(threadName + " get 13");
                            System.out.println("secondToFirst do work------------");
                            break;
                        } finally {
                            No13.unlock();
                        }
                    }
                } finally {
                    No14.unlock();
                }
            }
            Thread.sleep(random.nextInt(3));
        }
    }

    private static class TestThread extends Thread{

        private String name;

        public TestThread(String name) {
            this.name = name;
        }

        public void run(){
            Thread.currentThread().setName(name);
            try {
                secondToFirst();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String args[]){
        Thread.currentThread().setName("TestDeadLock");
        TestThread thread = new TestThread("SubTestThread");
        thread.start();
        try {
            firstToSecond();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
