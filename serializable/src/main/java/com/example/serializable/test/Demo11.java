package com.example.serializable.test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * author : fenzili
 * e-mail : 291924028@qq.com
 * date   : 2020/5/31 23:46
 * pkn    : com.example.serializable.test
 * desc   : 父类如果未实现序列化，子类如何序列化父类的field
 */
public class Demo11 {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Student student = new Student("");
        student.userName = "李俊杰";
        student.age = 29;
        student.setDesc("测试父类不序列化,子类序列化时,实现父类属性序列化");

//        FileOutputStream fos = new FileOutputStream("ceshi1.out");
//        ObjectOutputStream outputStream = new ObjectOutputStream(fos);
//        outputStream.writeObject(student);
//        outputStream.close();

        FileInputStream fis = new FileInputStream("ceshi1.out");
        ObjectInputStream bis = new ObjectInputStream(fis);
        Student student1 = (Student) bis.readObject();
        System.out.println(student1);
    }
}




