package com.example.thread.thread1;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.locks.ReentrantLock;

/**
 * author : fenzili
 * e-mail : 291924028@qq.com
 * date   : 2020/5/14 19:38
 * pkn    : com.example.thread.thread1
 * desc   : 线程启动方式
 */
public class ThreadWay {

    static class MyThread extends Thread {
        @Override
        public void run() {
            super.run();
        }
    }


    public static void main(String args[]) {
        new MyThread().start();
        new Thread(new Runnable() {
            @Override
            public void run() {

            }
        }).start();

        Callable callable = new Callable() {
            @Override
            public String call() throws Exception {
                return "this is a thread";
            }
        };
        FutureTask<String> runnableFuture = new FutureTask<String>(callable);
        new Thread(runnableFuture).start();

        ReentrantLock lock = new ReentrantLock();
        lock.lock();

    }

}
