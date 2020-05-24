package com.example.annotation_reflect.reflect.inject.Retrofit.retrofit.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * author : fenzili
 * e-mail : 291924028@qq.com
 * date   : 2020/5/6 21:35
 * pkn    : com.example.annotation_reflect.reflect.inject.Retrofit.retrofit.annotation
 * desc   :
 */
@Target(PARAMETER)
@Retention(RUNTIME)
public @interface Query {
    String value();
}
