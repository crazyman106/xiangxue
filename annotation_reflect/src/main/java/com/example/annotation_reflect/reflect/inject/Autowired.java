package com.example.annotation_reflect.reflect.inject;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * author : fenzili
 * e-mail : 291924028@qq.com
 * date   : 2020/4/26 22:44
 * pkn    : com.example.annotation_reflect.reflect.inject
 * desc   :
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Autowired {
    String value();
}
