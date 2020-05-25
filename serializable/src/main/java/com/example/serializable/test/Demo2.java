package com.example.serializable.test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Date;

/**
 * author : fenzili
 * e-mail : 291924028@qq.com
 * date   : 2020/5/25 23:06
 * pkn    : com.example.serializable
 * desc   : 测试transient
 */
public class Demo2 implements Serializable {
    private String name;

    private transient int age;

    private static long data = new Date().getTime();

    public Demo2(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Demo2{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public static void main(String args[]) throws IOException, ClassNotFoundException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(out);
        Demo2 demo1 = new Demo2("test serializable", 18);
        oos.writeObject(demo1);
        oos.close();

        byte[] bytes = out.toByteArray();

        ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
        ObjectInputStream ois = new ObjectInputStream(bis);

        Demo2 demo11 = (Demo2) ois.readObject();
        System.out.println(demo11);
    }
}

