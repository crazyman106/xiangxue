package com.example.thread.thread2.deadthread;

/**
 * author : fenzili
 * e-mail : 291924028@qq.com
 * date   : 2020/5/14 19:39
 * pkn    : com.example.thread.thread2
 * desc   : 线程死锁
 */
public class NormalDeadLock {

    static String TAG = NormalDeadLock.class.getSimpleName();

    private static Object obj3 = new Object();
    private static Object obj4 = new Object();


    private static void lock2() throws Exception {
        String name = Thread.currentThread().getName();
        synchronized (obj3) {
            System.out.println(name + " get obj3");
            Thread.sleep(100);
            synchronized (obj4) {
                System.out.println(name + " get obj4");
            }
        }
    }

    private static void lock1() throws Exception {
        String name = Thread.currentThread().getName();
        synchronized (obj4) {
            System.out.println(name + " get obj4");
            Thread.sleep(100);
            synchronized (obj3) {
                System.out.println(name + " get obj3");
            }
        }
    }

    static class MyThread extends Thread {

        private String name;

        public MyThread(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            for (int i=0;i<50;i++){
                System.out.println(name + " get obj4");
            }
            Thread.currentThread().setName(name);
            try {
                lock1();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String args[]) throws Exception {
        /*Thread.currentThread().setName("lijunjie");
        MyThread thread = new MyThread("haha");
        thread.start();
        lock2();*/

        MyThread myTread1=new MyThread("线程1");
        MyThread myTread2=new MyThread("线程2");
        MyThread myTread3=new MyThread("线程3");
        MyThread myTread4=new MyThread("线程4");
        for(int i=0;i<200;i++) {
            lock2();
        }
        myTread1.start();
        myTread2.start();
        myTread3.start();
        myTread4.start();
    }
}
