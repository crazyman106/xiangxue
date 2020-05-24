package com.example.genericsdemo.generic_wildcards;

import java.util.Arrays;
import java.util.List;

/**
 * author : fenzili
 * e-mail : 291924028@qq.com
 * date   : 2020/4/23 14:49
 * pkn    : com.example.genericsdemo.generic_wildcards
 * desc   :
 * <p>
 * 无界通配符类型使用通配符（ ? ）来指定
 * <p>
 * 有两种情况下，无界通配符是一种有用的方法。
 * 1. 如果你正在编写一个可以使用Object 类中提供的功能实现的方法。
 * 2. 当代码使用通用类中不依赖于类型参数的方法时。例如， List.size 或List.clear 。事实上，
 * Class 之所以这么经常使用，是因为Class 中的大部分方法都不依赖于T。
 */
public class Test {


    public static void process(List<? extends Number> list) {
        // 在Number类中定义的任何方法现在都可以在
        //elem上使用
        for (Number elem : list) {
// ...
        }
    }

    public static double sumOfList(List<? extends Number> list) {
        double s = 0.0;
        for (Number n : list)
            s += n.doubleValue();
        return s;
    }

    public void main() {
        List<Integer> li = Arrays.asList(1, 2, 3);
        System.out.println("sum = " + sumOfList(li));

        List<Double> ld = Arrays.asList(1.2, 2.3, 3.5);
        System.out.println("sum = " + sumOfList(ld));
    }

    public static void printList(List<Object> list) {
        for (Object elem : list)
            System.out.println(elem + " ");
        System.out.println();
    }

    /**
     * printList 的目标是打印任何类型的列表，但未能实现该目标（它仅打印Object 实例的列表）；它不
     * 能打印List 、List 、List 等，因为它们不是List 的子类型。要编写通用的printList 方法，请使
     * 用List ：
     */

    public static void printList1(List<?> list) {
        for (Object elem : list)
            System.out.print(elem + " ");
        System.out.println();
    }

    /**
     * 上限通配符将未知类型限制为特定类型或该类型的子类型，并使用extends 关
     * 键字表示。以类似的方式，下限通配符将未知类型限制为特定类型或该类型的超类型。
     * <p>
     * 你可以为通配符指定一个上限，也可以指定一个下限，但不能同时指定两者
     */

    public static void addNumbers(List<? super Integer> list) {
        for (int i = 1; i <= 10; i++) {
            list.add(i);
        }
    }
}

class WildcardFixed {
    void foo(List<?> i) {
        fooHelper(i);
    }

    // Helper method created so that the wildcard can be captured
// through type inference.
    private <T> void fooHelper(List<T> l) {
        l.set(0, l.get(0));
    }
}

class WildcardErrorBad {
    void swapFirst(List<? extends Number> l1, List<? extends Number> l2) {
        Number temp = l1.get(0);
//        l1.set(0, l2.get(0)); // expected a CAP#1 extends Number,
// got a CAP#2 extends Number;
// same bound, but different types
//        l2.set(0, temp); // expected a CAP#1 extends Number,
// got a Number
    }
}

/**
 * “输入”变量：输入变量将数据提供给代码。想象一个具有两个参数的复制方法： copy(src, dest) 。
 * src参数提供要复制的数据，因此它是输入参数。
 * “输出”变量：输出变量保存要在其它地方使用的数据。在复制示例copy(src, dest) 中，dest参数接
 * 受数据，因此它是输出参数。
 * 当然，某些变量既用于“输入”又用于“输出”目的（准则中也解决了这种情况）。
 * <p>
 * <p>
 * 使用上限通配符定义输入变量，使用extends 关键字。只可读
 * 使用下限通配符定义输出变量，使用super 关键字。只可写
 * <p>
 * 如果可以使用Object 类中定义的方法访问输入变量，请使用无界通配符（ ? ）
 * 如果代码需要同时使用输入和输出变量来访问变量，则不要使用通配符。
 * <p>
 * 这些准则不适用于方法的返回类型。应该避免使用通配符作为返回类型，因为这会迫使程序员使用代码
 * 来处理通配符。
 */
class NaturalNumber {
    private int i;

    public NaturalNumber(int i) {
        this.i = i;
    }
// ...
}

class EvenNumber extends NaturalNumber {
    public EvenNumber(int i) {
        super(i);
    }
// ...
}

/**
 * Erasure of Generic Types（泛型类型的擦除）
 * 在类型擦除过程中，Java编译器将擦除所有类型参数，如果类型参数是有界的，则将每个参数替换为其
 * 第一个边界；如果类型参数是无界的，则将其替换为Object
 */

class Node<T> {
    private T data;
    private Node<T> next;

    public Node(T data, Node<T> next) {
        this.data = data;
        this.next = next;
    }

    public T getData() {
        return data;
    }
// ...
}

/**
 * 由于类型参数T是无界的，因此Java编译器将其替换为Object ：
 * public class Node {
 * private Object data;
 * private Node next;
 * public Node(Object data, Node next) {
 * this.data = data;
 * this.next = next;
 * }
 * public Object getData() { return data; }
 * // ...
 * }
 */


// Java编译器将绑定类型参数T替换为第一个绑定类Comparable ：
class Node1<T extends Comparable<T>> {
    private T data;
    private Node1<T> next;

    public Node1(T data, Node1<T> next) {
        this.data = data;
        this.next = next;
    }

    public T getData() {
        return data;
    }
// ...
}

/**
 * Erasure of Generic Methods（通用方法的擦除）
 * Java编译器还会擦除通用方法参数中的类型参数
 */
class Test1 {
    public static <T> int count(T[] anArray, T elem) {
        int cnt = 0;
        for (T e : anArray)
            if (e.equals(elem))
                ++cnt;
        return cnt;
    }

    public static <T extends Shape> void draw(T shape) { /* ... */ }
}


class Shape { /* ... */
}

class Circle extends Shape { /* ... */
}

class Rectangle extends Shape { /* ... */
}