package com.example.genericsdemo.generic_method;

/**
 * author : fenzili
 * e-mail : 291924028@qq.com
 * date   : 2020/4/23 14:20
 * pkn    : com.example.genericsdemo.generic_method
 * desc   :
 */
public class Test {
    public static void main(String args[]) {
        Pair<Integer, String> p1 = new Pair<>(1, "apple");
        Pair<Integer, String> p2 = new Pair<>(2, "pear");
        //完整语法
        boolean same = Utils.<Integer, String>compare(p1, p2);

        // 类型推断
        // boolean same = Util.compare(p1, p2);
    }
}
