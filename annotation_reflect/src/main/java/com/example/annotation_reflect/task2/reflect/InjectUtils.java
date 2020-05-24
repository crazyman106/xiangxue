package com.example.annotation_reflect.task2.reflect;

import android.app.Activity;
import android.view.View;

import com.example.annotation_reflect.task2.annotation.EventType;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * author : fenzili
 * e-mail : 291924028@qq.com
 * date   : 2020/5/6 21:07
 * pkn    : com.example.annotation_reflect.task2.reflect
 * desc   :
 */
public class InjectUtils {

    /**
     * 1.获取目标对象中具体实现函数
     * 2.执行动态代理,将具体执行函数放在动态代理中
     * 3.通过动态代理获取代理对象.进行实际操作
     *
     * @param activity
     */
    public static void injectEvent(Activity activity) {
        Class<? extends Activity> cls = activity.getClass();
        Method[] methods = cls.getDeclaredMethods();
        for (Method method : methods) {
            //获得方法上所有注解
            Annotation[] annotations = method.getAnnotations();
            for (Annotation annotation : annotations) {
                Class<? extends Annotation> annotationType = annotation.annotationType();
                if (annotationType.isAnnotationPresent(EventType.class)) {
                    EventType eventType = annotationType.getAnnotation(EventType.class);
                    Class<?> listenerType = eventType.listenerType();
                    String listenerSetter = eventType.listenerSetter();

                    // 不需要关心到底是OnClick 还是 OnLongClick
                    try {
                        Method valueMethod = annotationType.getDeclaredMethod("value");
                        int[] viewIds = (int[]) valueMethod.invoke(annotation);
                        method.setAccessible(true);

                        ListenerInvocationHandler<Activity> handler = new ListenerInvocationHandler(activity, method);
                        Object listenerProxy = Proxy.newProxyInstance(listenerType.getClassLoader(), new Class[]{listenerType}, handler);
                        // 遍历注解的值
                        for (int viewId : viewIds) {
                            // 获得当前activity的view（赋值）
                            View view = activity.findViewById(viewId);
                            // 获取指定的方法(不需要判断是Click还是LongClick)
                            // 如获得：setOnClickLisnter方法，参数为OnClickListener
                            // 获得 setOnLongClickLisnter，则参数为OnLongClickLisnter
                            Method setter = view.getClass().getMethod(listenerSetter, listenerType);
                            // 执行方法
                            setter.invoke(view, listenerProxy); //执行setOnclickListener里面的回调 onclick方法,listenerProxy表示代理接口对象
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }


    /**
     * 还可能在自定义view注入，所以是泛型： T = Activity/View/Fragment
     *
     * @param <T>
     */
    static class ListenerInvocationHandler<T> implements InvocationHandler {

        private Method method;
        private T target;

        public ListenerInvocationHandler(T target, Method method) {
            this.target = target;
            this.method = method;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            return this.method.invoke(target, args);
        }
    }
}
