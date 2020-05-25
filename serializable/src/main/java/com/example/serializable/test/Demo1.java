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
 * desc   : 正常的序列化/反序列化
 */
public class Demo1 implements Serializable {
    private String name;
    private int age;

    public Demo1(String name, int age) {
        this.name = name;
        this.age = age;
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
        Demo1 demo1 = new Demo1("test serializable",18);
        oos.writeObject(demo1);
        oos.close();

        byte[] bytes = out.toByteArray();

        ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
        ObjectInputStream ois = new ObjectInputStream(bis);

        Demo1 demo11 = (Demo1) ois.readObject();
        System.out.println(demo11);
    }
}
