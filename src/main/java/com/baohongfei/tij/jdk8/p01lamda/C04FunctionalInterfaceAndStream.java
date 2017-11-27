package com.baohongfei.tij.jdk8.p01lamda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 2017/11/21 11:06 PM by hofer.bao
 */
@FunctionalInterface
interface TheInterface {
    void myMethod();
}

@FunctionalInterface
interface TheInterface2 {
    void myMethod2();
}

public class C04FunctionalInterfaceAndStream {
    public static void main(String[] args) {
        TheInterface i1 = () -> {
        };
        System.out.println(i1.getClass().getInterfaces()[0]);


        TheInterface2 i2 = () -> {
        };
        System.out.println(i2.getClass().getInterfaces()[0]);

        new Thread(() -> System.out.println("hello thread")).start();

        List<String> list = Arrays.asList("hello", "world", "hello world");

        list.forEach(item -> System.out.println(item.toUpperCase()));

        List<String> list2 = new ArrayList<>();

        list.forEach(item -> list2.add(item.toUpperCase()));
        list2.forEach(item -> System.out.println(item));

        list.stream().map(item -> item.toUpperCase()).forEach(item -> System.out.println(item));

        list.stream().map(String::toUpperCase).forEach(System.out::println);


    }
}

