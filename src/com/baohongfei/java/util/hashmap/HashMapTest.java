package com.baohongfei.java.util.hashmap;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by terry on 17/4/11.
 */
public class HashMapTest {
    public static void main(String[] args) {
        Map testMap = new HashMap();
        testMap.put(null,null);
        testMap.put(1,2);
        testMap.put("key","value");

        Set<Map.Entry> entries = testMap.entrySet();
        for(Map.Entry entry: entries){
            System.out.println("key = " + entry.getKey() + ",value = "+ entry.getValue());
        }

        Map synchronizedMap = Collections.synchronizedMap(testMap);

    }
}
