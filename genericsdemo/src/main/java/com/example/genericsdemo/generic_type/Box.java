package com.example.genericsdemo.generic_type;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * author : fenzili
 * e-mail : 291924028@qq.com
 * date   : 2020/4/21 20:34
 * pkn    : com.example.genericsdemo.generic_type
 * desc   :
 */
public class Box {
    public <T extends ViewGroup> T getView(Context context) {
        return null;
    }

    private Object object;

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public static void main(String args[]) {
        Box box = new Box();
        box.setObject(1);
        System.out.println(box.getObject());
        box.setObject("李俊杰");
        System.out.println(box.getObject());
    }
}

/**
 * 类型变量可以是你指定的任何非基本类型:任何类类型，
 * 任何接口类型，任何数组类型，甚至是另一个类型变量
 *
 * @param <T>
 */
class Box1<T> {
    // T stands for "Type"
    private T t;

    public void set(T value) {
        this.t = value;
    }

    public T get() {
        return t;
    }

    // 要从代码中引用通用Box类，必须执行通用类型调用，该调用将T替换为某些具体值
    public static void main(String args[]) {
        // 泛型类型的调用通常称为参数化类型
        Box1<Integer> integerBox = new Box1<Integer>();
        // T是类型参数（形参）
        // Integer:类型参数（实参）

        // 原始类型是没有任何类型参数的泛型类或接口的名称
//        Box1 integerBox1 = new Box1();


        // 原始类型会绕过通用类型检查，从而将不安全代码的捕获推迟到运行时。因此，应避免使用原始类型。
//        Box1<String> stringBox = new Box1<>();
//        Box1 rawBox = stringBox;
//        rawBox.set(8);
    }
}

//泛型接口:T称为类型变量
interface Generics<T> {

}

// 泛型类/接口的继承和实现
class Generics2 implements Generics<String> {
    public static void main(String args) {
        Generics2 generics = new Generics2();
    }
}

class Generics3<T> implements Generics<T> {
    public static void main(String args) {
        Generics3<Integer> generics = new Generics3();
    }
}

interface Pair<K, V> {
    public K getKey();

    public V getValue();
}

class OrderedPair<K, V> implements Pair<K, V> {

    private K key;
    private V value;

    public OrderedPair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    public static void main(String args[]) {
        OrderedPair<String, Integer> p1 = new OrderedPair<>("Even", 8);
        OrderedPair<String, String> p2 = new OrderedPair<>("hello", "world");
        // 参数化类型
        OrderedPair<String, Box1<Integer>> p3 = new OrderedPair<>("primes", new Box1<Integer>());
    }
}