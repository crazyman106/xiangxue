package com.example.serializable.test;

import java.io.Serializable;

class Student extends Person implements Serializable {
    private String desc;
    public static final int serialVersionUID = 1;

    private String sex;

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Student(String id) {
        super(id);
    }

    @Override
    public String toString() {
        return "Student{" +
                "desc='" + desc + '\'' +
                ", sex='" + sex + '\'' +
                ", userName='" + userName + '\'' +
                ", age=" + age +
                '}';
    }

    private void writeObject(java.io.ObjectOutputStream out) throws Exception {
        out.defaultWriteObject(); //先序列化对象
        out.writeUTF(userName);// 再序列化父类的域
        out.writeUTF(desc);
        out.writeInt(age);
    }

    private void readObject(java.io.ObjectInputStream in) throws Exception {
        in.defaultReadObject();
        userName = in.readUTF();
        desc = in.readUTF();
        age = in.readInt();
    }
}