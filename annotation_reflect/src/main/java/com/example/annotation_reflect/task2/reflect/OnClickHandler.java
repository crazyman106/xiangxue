package com.example.annotation_reflect.task2.reflect;

import android.app.Activity;
import android.util.Log;

import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.HashMap;

/**
 * author : fenzili
 * e-mail : 291924028@qq.com
 * date   : 2020/5/3 22:02
 * pkn    : com.example.annotation_reflect.task2.reflect
 * desc   :
 */
public class OnClickHandler implements InvocationHandler {

    // 缓存,用来存储函数:k-v
    private final HashMap<String, Method> methodMap = new HashMap<String, Method>(
            1);
    private WeakReference<Object> handlerRef;

    public OnClickHandler(Object object) {
        handlerRef = new WeakReference<>(object);
    }

    public void addMethod(String name, Method method) {
        methodMap.put(name, method);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 当回调OnClickListener的OnClick方法的时候，它会调用这里的invoke方法
        Object handler = handlerRef.get();
        Log.e("OnClick","22222222222222222");
        if (handler != null) {
            // method对应的就是回调方法OnClick，得到方法名
            String name = method.getName();
            method = methodMap.get(name);
            if (method != null) {
                return method.invoke(handler, args);
            }
        }
        return null;
    }
}
