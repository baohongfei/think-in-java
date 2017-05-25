package com.baohongfei.common;

import java.util.concurrent.atomic.AtomicInteger;

public class HelloWorld {
    public static void main(String[] args) {
        AtomicInteger i = new AtomicInteger(0);
        f(i);
        System.out.println(i);
    }

    private static void f(AtomicInteger i) {
        i.set(i.intValue()+1);
    }
}

