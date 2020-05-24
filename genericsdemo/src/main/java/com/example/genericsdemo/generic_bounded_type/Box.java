package com.example.genericsdemo.generic_bounded_type;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Box<T> {
    private T t;

    public void set(T t) {
        this.t = t;
    }

    public T get() {
        return t;
    }

    // 限定类型参数:想限制可以在参数化类型中用作类型参数的类型
    public <U extends Number> void inspect(U u) {
        System.out.println("T: " + t.getClass().getName());
        System.out.println("U: " + u.getClass().getName());
    }

    public static void main(String[] args) {
        Box<Integer> integerBox = new Box<Integer>();
        integerBox.set(new Integer(10));
        // integerBox.inspect("some text"); // error: this is still String!
    }
}

// 限定类型参数还允许你调用在范围中定义的方法,Inteter的intValue()

/**
 * 一个类型参数可以具有多个限定,<T extends 1 B1 & B2 & B3>:允许一个泛型类和多个泛型接口,泛型类必须在最前边
 *
 * @param <T>
 */
class NaturalNumber<T extends Integer> {
    private T n;

    public NaturalNumber(T n) {
        this.n = n;
    }

    public boolean isEven() {
        return n.intValue() % 2 == 0;
    }

    public static <T extends Comparable<T>> int countGreaterThan(T[] anArray, T elem) {
        int count = 0;
        // 它不能编译，因为大于运算符（ > ）仅适用于基本类型
        /*for (T e : anArray)
            if (e > elem) // compiler error
                ++count;*/
        for (T e : anArray) {
            if (e.compareTo(elem) > 0)
                ++count;
        }
        return count;
    }
}

interface Comparable<T> {
    public int compareTo(T o);
}

// 泛型，继承和 子类型

class Test {

    public void test() {
        Object someObject = new Object();
        Integer someInteger = new Integer(10);
        someObject = someInteger; // OK
        // Object 是Integer 的超类型之一

        someMethod(new Integer(1));
        someMethod(new Double(1.0));

        // 可以执行通用类型调用，将Number 作为其类型参数传递，并且如果该参数与
        //Number 兼容,则可以随后进行set的任何后续调用
        Box<Number> box = new Box<Number>();
        box.set(new Integer(10)); // OK
        box.set(new Double(10.1)); // OK

//        Box<Number>和Box<Integer>是两种类型
    }

    public void someMethod(Number n) { /* ... */ }


    static <T> T pick(T a1, T a2) {
        return a2;
    }

    Serializable s = pick("d", new ArrayList<String>());
}

/**
 * PayloadList<String,String>
 * PayloadList<String,Integer>
 * PayloadList<String,Exception>
 * 都是List<T>的子类型
 *
 * @param <E>
 * @param <P>
 */
interface PayloadList<E, P> extends List<E> {
    void setPayload(int index, P val);
}


class MyClass<X> {
    <T> MyClass(T t) {
    }


    public void main(){
//        new MyClass<Integer>("");
        MyClass<Integer> myObject = new MyClass<>("");
    }
}