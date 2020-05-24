package com.example.annotation_reflect.task2.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * author : fenzili
 * e-mail : 291924028@qq.com
 * date   : 2020/4/29 17:21
 * pkn    : com.example.annotation_reflect.task2.annotation
 * desc   :
 */
@Target(ElementType.ANNOTATION_TYPE) // 放在注解的上面
@Retention(RetentionPolicy.RUNTIME)
public @interface EventType {
    // 1、set方法名 setOnclickListener()
    String listenerSetter();

    // 2、监听的对象 View.OnclickListener
    Class<?> listenerType();
}
