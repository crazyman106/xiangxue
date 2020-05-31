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
 * desc   : 多引用
 */
public class Demo7 implements Serializable {
    private String name;
    private int age;

    public Demo7(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setAge(int age) {
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
        Demo7 demo1 = new Demo7("test serializable", 18);
        oos.writeObject(demo1);
        demo1.setAge(25);
//        oos.writeUnshared(demo1);
        oos.reset();
        oos.writeObject(demo1);
        oos.close();

        byte[] bytes = out.toByteArray();

        ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
        ObjectInputStream ois = new ObjectInputStream(bis);

        Demo7 demo11 = (Demo7) ois.readObject();
        Demo7 demo12 = (Demo7) ois.readObject();
        System.out.println(demo11 + "==" + demo12);
    }
}
