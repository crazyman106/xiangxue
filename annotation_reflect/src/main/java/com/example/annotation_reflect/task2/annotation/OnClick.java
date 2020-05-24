package com.example.annotation_reflect.task2.annotation;

import android.view.View;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * author : fenzili
 * e-mail : 291924028@qq.com
 * date   : 2020/4/29 16:45
 * pkn    : com.example.annotation_reflect.task2.annotation
 * desc   :注释,反射,动态代理
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@EventType(listenerType = View.OnClickListener.class, listenerSetter = "setOnClickListener")
public @interface OnClick {
    int[] value();
}
