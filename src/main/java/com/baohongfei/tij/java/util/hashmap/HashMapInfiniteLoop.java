package com.baohongfei.tij.java.util.hashmap;

import java.util.HashMap;


/**
 * Created by terry on 17/4/11.
 *
 * @see https://tech.meituan.com/java-hashmap.html
 *
 */
public class HashMapInfiniteLoop {

    private static HashMap<Integer, String> map = new HashMap<Integer, String>(2, 0.75f);

    public static void main(String[] args) {
        map.put(5, "C");

        new Thread("Thread1") {
            public void run() {
                map.put(7, "B");
                System.out.println(map);
            }
        }.start();
        new Thread("Thread2") {

            public void run() {
                map.put(3, "A");
                System.out.println(map);
            }
        }.start();

        map.get(11);
    }
}