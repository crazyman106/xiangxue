package com.example.serializable.test;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
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
public class Demo8 implements Serializable {
    private String name;
    private int age;

    private final static long serialVersionUID = -5057756182968445165L;

    public Demo8(String name, int age) {
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
        return "Demo8{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", height=" + height +
                '}';
    }

    private float height;
    public static void main(String args[]) throws IOException, ClassNotFoundException {
       /* FileOutputStream out = new FileOutputStream("a.out");
        ObjectOutputStream oos = new ObjectOutputStream(out);
        Demo8 demo1 = new Demo8("test serializable", 18);
        oos.writeObject(demo1);
        oos.close();*/


        FileInputStream bis = new FileInputStream("a.out");
        ObjectInputStream ois = new ObjectInputStream(bis);

        Demo8 demo11 = (Demo8) ois.readObject();
        System.out.println(demo11 );

        Message message = Message.obtain(new Handler(Looper.myLooper()), () -> {

        });
    }
}
