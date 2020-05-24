package com.example.serializable.work;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Date;

import static java.sql.DriverManager.println;

/**
 * author : fenzili
 * e-mail : 291924028@qq.com
 * date   : 2020/5/25 0:16
 * pkn    : com.example.serializable.work
 * desc   :
 */
public class Serial implements Serializable {
    private static Date date = new Date();
    private String name;

    static final long serialVersionUID = 42L;

    public Serial(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Serial{" +
                "name='" + name + '\''+date+
                '}';
    }


    public static void main(String[] args) throws IOException, ClassNotFoundException {
       /* ByteArrayOutputStream out = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(out);
        oos.writeObject(new Serial("haha"));
//    student.score = 78f
// oos.reset();
        // oos.reset();
//    oos.writeUnshared(student)
// oos.writeObject(course);
        // oos.writeObject(course);
        byte[] bytes = out.toByteArray();
        oos.close();
        ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(bytes));
        Serial student1 = (Serial) ois.readObject();
//    val student2 = ois.readObject() as Student
        System.out.println(student1);*/

       Serial object = new Serial("haha");

        // 创建ObjectOutputStream对象输出流，其中用到了文件的描述符对象和文件输出流对象
        ObjectOutputStream oo = new ObjectOutputStream(new FileOutputStream(
                new File("DataObject.txt")));

        // 将DataObject对象存储到DataObject.txt文件中，完成对DataObject对象的序列化操作
        oo.writeObject(object);

        System.out.println("Person对象序列化成功！"+object.toString());

        // 最后一定记得关闭对象描述符！！！
        oo.close();

        //  25 00:34:24 CST 2020
        //  25 00:34:48 CST 2020

        // 创建ObjectInputStream对象输入流，其中用到了文件的描述符对象和文件输入流对象
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(
                new File("DataObject.txt")));

        // 从DataObject.txt文件中读取DataObject对象，完成对DataObject对象的反序列化操作
        Serial object1 = (Serial) ois.readObject();
        System.out.println("Person对象反序列化成功！"+object1.toString()+";"+(object ==object1 ));

        // 最后一定记得关闭对象描述符！！！
        ois.close();
    }

}
