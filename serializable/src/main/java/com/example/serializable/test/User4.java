package com.example.serializable.test;

import java.io.Serializable;

/**
 * author : fenzili
 * e-mail : 291924028@qq.com
 * date   : 2020/5/25 23:23
 * pkn    : com.example.serializable.test
 * desc   :
 */
public class User4 implements Serializable {
    private String userName;

    @Override
    public String toString() {
        return "User3{" +
                "userName='" + userName + '\'' +
                '}';
    }

    public User4(String name) {
        this.userName = name;
    }
}
