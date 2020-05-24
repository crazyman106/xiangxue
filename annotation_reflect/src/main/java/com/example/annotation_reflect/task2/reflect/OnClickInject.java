package com.example.annotation_reflect.task2.reflect;

import android.app.Activity;
import android.util.Log;
import android.view.View;

import com.example.annotation_reflect.task2.annotation.OnClickListener;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * author : fenzili
 * e-mail : 291924028@qq.com
 * date   : 2020/5/3 21:26
 * pkn    : com.example.annotation_reflect.task2.reflect
 * desc   :
 */
public class OnClickInject {
    public static void injectOnClick(final Activity activity) {
        Class<? extends Activity> activityClass = activity.getClass();
        Method[] activityClassDeclaredMethods = activityClass.getDeclaredMethods();
        for (Method classDeclaredMethod : activityClassDeclaredMethods) {
            if (classDeclaredMethod.isAnnotationPresent(OnClickListener.class)) {
                OnClickListener methodAnnotation = classDeclaredMethod.getAnnotation(OnClickListener.class);
                // 获取OnClick注解上的注解类型
                OnClickListener annotation = classDeclaredMethod.getAnnotation(OnClickListener.class);
                int[] viewIds = annotation.value();
                final Method method1 = classDeclaredMethod;
                // 实际点击功能会调用这个地方,具体实现在invoke中
                /*Object clickListener = Proxy.newProxyInstance(View.OnClickListener.class.getClassLoader(), new Class[]{View.OnClickListener.class}, new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        // method为View的click函数,在这个地方我们调用activity中自定义的onClick函数
                        return  method1.invoke(activity, args);
                    }
                });*/
                // 将InvocationHandler抽出来,将需要实现的内容放在它中,实现一些资源优化
                OnClickHandler clickHandler = new OnClickHandler(activity);
                Object clickListener = Proxy.newProxyInstance(View.OnClickListener.class.getClassLoader(), new Class[]{View.OnClickListener.class}, clickHandler);
                clickHandler.addMethod(classDeclaredMethod.getName(), classDeclaredMethod);

                // 这个地方类似于仿Retrofit中在Activity中调用Api接口的的函数,实际上回调用动态里中的invoke(具体实现为InvocationHanlder中的invoke())
                for (int viewId : viewIds) {
                    try {
                        // 获取activity中findViewById函数
                        Method findViewById = activityClass.getMethod("findViewById", int.class);
                        findViewById.setAccessible(true);
                        // 通过findViewById和viewId反射获取View控件
                        View view = (View) findViewById.invoke(activity, viewId);
                        // 获取view的点击方法
                        Method setOnClickListener = view.getClass().getMethod("setOnClickListener", View.OnClickListener.class);
                        setOnClickListener.setAccessible(true);
                        // 通过反射调用view的setOnClickListener方法,clickListener表示代理接口,即我们要实现功能的接口
                        setOnClickListener.invoke(view, clickListener);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
