package com.example.thread.thread2.threadlocal;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * author : fenzili
 * e-mail : 291924028@qq.com
 * date   : 2020/5/16 23:43
 * pkn    : com.example.thread.thread2.threadlocal
 * desc   :
 */
public class UserThreadLocal {
    //    static MyThreadLocal<String> threadLocal1 = new MyThreadLocal<>();
    static MyThreadLocal<Integer> threadLocal2 = new MyThreadLocal<>();
    static ThreadLocal<String> threadLocal1 = new ThreadLocal<>();
    Executor pool = Executors.newFixedThreadPool(3);


    public static void main(String args[]) {
        UserThreadLocal test = new UserThreadLocal();
        test.startThread();

        test.pool.execute(new Runnable() {
            @Override
            public void run() {

            }
        });
    }

    public void startThread() {
        Thread threads[] = new Thread[1000];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(new TestThread(i));
        }
        for (Thread thread : threads) {
            thread.start();
        }
    }


    static class TestThread implements Runnable {

        int id;

        public TestThread(int id) {
            this.id = id;
        }

        @Override
        public void run() {
            String name = Thread.currentThread().getName();
            threadLocal1.set("线程: " + id);
            System.out.println(name + ":" + threadLocal1.get());
        }
    }
}
