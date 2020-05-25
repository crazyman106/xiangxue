package com.example.serializable.test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;

/**
 * author : fenzili
 * e-mail : 291924028@qq.com
 * date   : 2020/5/25 23:06
 * pkn    : com.example.serializable
 * desc   : 反序列化一个类的过程中，它的非可序列化的属性将会调用无参构造函数重新创建
 */

// TODO 没有实现
public class Demo5 implements Serializable {
    private String name;
    private int age;
    private User5 user5;

    public Demo5(String name, int age,User5 user5) {
        this.name = name;
        this.age = age;
        this.user5 = user5;
    }

    @Override
    public String toString() {
        return "Demo1{" +
                "name='" + name + '\'' +
                ", age=" + age +'\'' +
                ", user5=" + user5.toString() +
                '}';
    }

    public static void main(String args[]) throws IOException, ClassNotFoundException {
       /* FileOutputStream out = new FileOutputStream("test5.out");
        ObjectOutputStream oos = new ObjectOutputStream(out);
        Demo5 demo1 = new Demo5("test serializable",18,new User5("我是谁"));
        oos.writeObject(demo1);
        oos.close();*/


        FileInputStream bis = new FileInputStream("test5.out");
        ObjectInputStream ois = new ObjectInputStream(bis);

        Demo5 demo11 = (Demo5) ois.readObject();
        System.out.println(demo11);
    }
}
