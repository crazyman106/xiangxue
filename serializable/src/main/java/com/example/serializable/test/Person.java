package com.example.serializable.test;

public class Person {

    // 如果没有该构造函数:java.io.InvalidClassException: com.example.serializable.test.Student; no valid constructor
    public Person() {
    }
    public Person(String id){

    }

    String userName;
    int age;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Person(String userName, int age) {
        this.userName = userName;
        this.age = age;
    }
}
