package com.example.serializable.test;

/**
 * author : fenzili
 * e-mail : 291924028@qq.com
 * date   : 2020/5/25 23:23
 * pkn    : com.example.serializable.test
 * desc   :
 */
public class User6 {
    private String name;
    private String sax;

    public String getName() {
        return name;
    }

    public String getSax() {
        return sax;
    }
    public User6() {
        this.name ="test1--name";
        this.sax ="test1--sax";
        System.out.println("empty constructor");
    }

    public User6(String name, String sax) {
        this.name = name;
        this.sax = sax;
    }
}
