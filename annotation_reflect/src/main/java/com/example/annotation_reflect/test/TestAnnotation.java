package com.example.annotation_reflect.test;

import com.example.annotation_reflect.annotation.Lance;
import com.example.annotation_reflect.annotation.Teacher;

/**
 * author : fenzili
 * e-mail : 291924028@qq.com
 * date   : 2020/4/28 13:34
 * pkn    : com.example.annotation_reflect.test
 * desc   :
 */
@Lance
public class TestAnnotation {
    public static final int LANCE = 1;
    public static final int ALVIN = 2;

    public static void main(String args[]){
        test(LANCE);
    }

    public static void test(@Teacher int teacher) {
    }
}

