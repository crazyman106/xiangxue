package com.example.serializable.test;

import java.io.Serializable;

/**
 * author : fenzili
 * e-mail : 291924028@qq.com
 * date   : 2020/5/25 23:23
 * pkn    : com.example.serializable.test
 * desc   :
 */
public class User5 {
    private String name;



    @Override
    public String toString() {
        return "User3{" +
                "name='" + name + '\'' +
                '}';
    }

    public User5() {
        System.out.println("empty constructor");
    }
}
