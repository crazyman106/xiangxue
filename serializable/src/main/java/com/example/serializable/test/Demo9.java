package com.example.serializable.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

/**
 * 枚举,
 */
public enum Demo9 {
    ONE, TWO, THREE;

    public void printValues() {
        System.out.println(ONE + " ONE.ordinal " + ONE.ordinal());
        System.out.println(TWO + " TWO.ordinal " + TWO.ordinal());
        System.out.println(THREE + " THREE.ordinal " + THREE.ordinal());
    }

    public static void testSerializable() throws Exception {
        File file = new File("p.dat");
//        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
//        oos.writeObject(Demo9.ONE);
//        oos.close();
        Demo9.TWO.printValues();
        System.out.println("=========反序列化后=======");
        ObjectInputStream ois = new ObjectInputStream(new
                FileInputStream(file));
        Demo9 s1 = (Demo9) ois.readObject();
        s1.printValues();
        ois.close();
    }

    public static void main(String... args) throws Exception {
        testSerializable();
    }
}

