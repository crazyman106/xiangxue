package com.example.serializable.test;

/**
 * author : fenzili
 * e-mail : 291924028@qq.com
 * date   : 2020/5/25 23:23
 * pkn    : com.example.serializable.test
 * desc   :
 */
public class User3 {
    private String name;

    @Override
    public String toString() {
        return "User3{" +
                "name='" + name + '\'' +
                '}';
    }

    public User3(String name) {
        this.name = name;
    }
}
