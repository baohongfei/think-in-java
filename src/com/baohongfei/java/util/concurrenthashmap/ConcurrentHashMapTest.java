package com.baohongfei.java.util.concurrenthashmap;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by terry on 21/07/17.
 */
public class ConcurrentHashMapTest {
    public static void main(String[] args) {
        ConcurrentHashMap map = new ConcurrentHashMap();
        map.put(1,2);
        map.get(1);
        map.size();
    }
}
