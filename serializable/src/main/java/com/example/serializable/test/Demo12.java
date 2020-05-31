package com.example.serializable.test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * author : fenzili
 * e-mail : 291924028@qq.com
 * date   : 2020/5/25 23:06
 * pkn    : com.example.serializable
 * desc   : 正常的序列化/反序列化
 */
public class Demo12 implements Externalizable {
    private String name;
    private int age;
    public Demo12() {
        System.out.println("Empty Constructor");
    }
    public Demo12(String name, int age) {
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
        Demo12 demo1 = new Demo12("test serializable", 18);
        oos.writeObject(demo1);
        oos.close();
        System.out.println("序列化成功");

        byte[] bytes = out.toByteArray();

        ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
        ObjectInputStream ois = new ObjectInputStream(bis);

        Demo12 demo11 = (Demo12) ois.readObject();
        System.out.println(demo11);
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        System.out.println("writeExternal");
        out.writeUTF(name);
        out.writeInt(age);
    }

    @Override
    public void readExternal(ObjectInput in) throws ClassNotFoundException, IOException {
        System.out.println("readExternal");
        name = in.readUTF();
        age = in.readInt();
    }

    //Exception in thread "main" java.io.InvalidClassException: com.example.serializable.test.Demo12; no valid constructor
    /**
     * writeExternal
     * 序列化成功
     * Exception in thread "main" java.io.InvalidClassException: com.example.serializable.test.Demo12; no valid constructor
     * 	at java.io.ObjectStreamClass$ExceptionInfo.newInvalidClassException(ObjectStreamClass.java:169)
     * 	at java.io.ObjectStreamClass.checkDeserialize(ObjectStreamClass.java:874)
     * 	at java.io.ObjectInputStream.readOrdinaryObject(ObjectInputStream.java:2043)
     * 	at java.io.ObjectInputStream.readObject0(ObjectInputStream.java:1573)
     * 	at java.io.ObjectInputStream.readObject(ObjectInputStream.java:431)
     * 	at com.example.serializable.test.Demo12.main(Demo12.java:52)
     */

    /**
     * writeExternal
     * 序列化成功
     * Empty Constructor
     * readExternal
     * Demo1{name='test serializable', age=18}
     */
}
