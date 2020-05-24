package com.example.annotation_reflect.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import androidx.annotation.IntDef;

import static com.example.annotation_reflect.test.TestAnnotation.ALVIN;
import static com.example.annotation_reflect.test.TestAnnotation.LANCE;

@IntDef(value = {LANCE, ALVIN}) //限定为LANCE，ALVIN
@Target(ElementType.PARAMETER) //作用于参数的注解
@Retention(RetentionPolicy.SOURCE) //源码级别注解
public @interface Teacher {
}