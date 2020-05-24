package com.example.annotation_reflect.task2.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * author : fenzili
 * e-mail : 291924028@qq.com
 * date   : 2020/5/3 21:19
 * pkn    : com.example.annotation_reflect.task2.annotation
 * desc   :
 *
 * View.OnClickListener.class
 * setOnClickListener(OnClickListener l)
 * onClick(View view)
 *
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface OnClickListener {
    int[] value();
}
