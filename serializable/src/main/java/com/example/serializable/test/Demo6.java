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
public class Demo6  implements Serializable {

    public static void main(String args[]) throws IOException, ClassNotFoundException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(out);
        User6Child demo1 = new User6Child("test serializable","男",12);
        oos.writeObject(demo1);
        oos.close();
        System.out.println("====== ==========");
        byte[] bytes = out.toByteArray();

        ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
        ObjectInputStream ois = new ObjectInputStream(bis);

        User6Child demo11 = (User6Child) ois.readObject();
        System.out.println(demo11);
    }
}
