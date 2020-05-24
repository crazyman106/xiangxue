package com.example.annotation_reflect.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * author : fenzili
 * e-mail : 291924028@qq.com
 * date   : 2020/4/26 20:08
 * pkn    : com.example.annotation_reflect.annotation
 * desc   :
 * <p>
 * 元注解:注解上的注解--Target(制定作用在什么地方),Retention
 */
@Target(ElementType.TYPE) // 可以指定多个
@Retention(RetentionPolicy.SOURCE)
// 保留级别:source保留在源码阶段;class保留在class文件中,但是jvm加载时,会忽略它;runtime运行时通过反射加载他;ANNOTATION_TYPE表示该注解是元注解
public @interface Lance {
    String value() default "lijunjie"; // 有默认值就可以不赋值,否则必须赋值;只有value时,可以直接赋值,否则使用时,就以key-value赋值

}
