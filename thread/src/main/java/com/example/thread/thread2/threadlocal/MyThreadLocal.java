package com.example.thread.thread2.threadlocal;

import java.util.HashMap;
import java.util.Map;

/**
 * author : fenzili
 * e-mail : 291924028@qq.com
 * date   : 2020/5/16 23:40
 * pkn    : com.example.thread.thread2.threadlocal
 * desc   : 自定义实现ThreadLocal
 * 问题:并发,并发访问速度慢
 */
public class MyThreadLocal<T> {
    private Map<Thread, T> threadMap = new HashMap<>();

    public synchronized T get() {
        return threadMap.get(Thread.currentThread());
    }

    public synchronized void set(T value) {
        this.threadMap.put(Thread.currentThread(), value);
    }
}
