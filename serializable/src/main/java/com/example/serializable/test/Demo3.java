package com.example.serializable.test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * author : fenzili
 * e-mail : 291924028@qq.com
 * date   : 2020/5/25 23:06
 * pkn    : com.example.serializable
 * desc   : 测试成员不序列化
 */
public class Demo3 implements Serializable {
    private String name;
    private int age;
    private User3 user;

    public Demo3(String name, int age,User3 user) {
        this.name = name;
        this.age = age;
        this.user = user;
    }

    @Override
    public String toString() {
        return "Demo1{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public static void main(String args[]) throws IOException, ClassNotFoundException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(out);
        Demo3 demo1 = new Demo3("test serializable",18,new User3("我是谁?"));
        oos.writeObject(demo1);
        oos.close();

        byte[] bytes = out.toByteArray();

        ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
        ObjectInputStream ois = new ObjectInputStream(bis);

        Demo3 demo11 = (Demo3) ois.readObject();
        System.out.println(demo11);
    }
}
