package com.example.serializable.test;

import java.io.Serializable;

/**
 * author : fenzili
 * e-mail : 291924028@qq.com
 * date   : 2020/5/25 23:23
 * pkn    : com.example.serializable.test
 * desc   :
 */
public class User6Child extends User6 implements Serializable {
    private int age;

    public User6Child(String name,String sex, int age) {
        super(name,sex);
        this.age = age;
    }

    @Override
    public String toString() {
        return "User6Child{" +
                "age=" + age +
                '}'+super.getName()+"--"+super.getSax();
    }
}
