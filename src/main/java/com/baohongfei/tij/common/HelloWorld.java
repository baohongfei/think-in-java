package com.baohongfei.tij.common;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;

public class HelloWorld {
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger();
        System.out.println(atomicInteger.get());
        System.out.println(atomicInteger.incrementAndGet());
        AtomicStampedReference atomicStampedReference = new AtomicStampedReference(1,0);
        System.out.println(atomicInteger);
    }
}

