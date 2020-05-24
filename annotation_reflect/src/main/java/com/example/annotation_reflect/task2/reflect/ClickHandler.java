package com.example.annotation_reflect.task2.reflect;

import android.util.Log;

import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.HashMap;

/**
 * author : fenzili
 * e-mail : 291924028@qq.com
 * date   : 2020/4/29 17:28
 * pkn    : com.example.annotation_reflect.task2.reflect
 * desc   :
 */
public class ClickHandler implements InvocationHandler {
    private final HashMap<String, Method> methodMap = new HashMap<String, Method>(
            1); // 只存储一个函数onClick,如果需要存储多种类型的点击,可以手动扩容
    // 因为传进来的为activity，使用弱引用主要是为了防止内存泄漏
    private WeakReference<Object> handlerRef;

    public ClickHandler(Object obj) {
        handlerRef = new WeakReference<>(obj);
    }

    public void addMethod(String name, Method method) {
        methodMap.put(name, method);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Log.e("OnClickInject1",method.toString());
        // 当回调OnClickListener的OnClick方法的时候，它会调用这里的invoke方法
        Object handler = handlerRef.get();
        if (handler != null) {
            // method对应的就是回调方法onClick，得到方法名
            String name = method.getName();
            // 得到activtiy里面的onClick方法
            method = methodMap.get(name);
            if (method != null) {
                return method.invoke(handler, args);
            }
        }
        return null;
    }
}
