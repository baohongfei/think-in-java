package com.baohongfei.tij.jdk8.p01lamda;

import java.util.function.Function;

/**
 * @author hofer.bhf
 * created on 2017/11/27 10:53 PM
 */
public class C06Function {

    public int compute(int a, Function<Integer, Integer> function) {
        int result = function.apply(a);
        return result;
    }

    public String convert(int a, Function<Integer, String> function) {
        return function.apply(a);
    }

    public static void main(String[] args) {

        C06Function test = new C06Function();
        System.out.println(test.compute(1, value -> 2 * value));
        System.out.println(test.compute(2, value -> 5 + value));
        System.out.println(test.compute(3, value -> value * value));

        System.out.println(test.convert(3, value -> String.valueOf(value + "helloworld")));


    }
}
