package com.baohongfei.java.lang.ref;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.WeakHashMap;

/**
 * Created by terry on 18/07/17.
 */
public class ReferenceTest {
    public static void main(String[] args) {
        //使用软引用构建敏感数据的缓存
        String str = new String("Hello");
        ReferenceQueue queue = new ReferenceQueue();
        SoftReference<String> softReferenceString = new SoftReference<>(str,queue);
        str = null;
        System.gc();
        System.out.println(softReferenceString.get());

        WeakReference<String> weakReferenceString = new WeakReference<>(new String("World"));
        System.out.println(weakReferenceString.get());

        WeakHashMap weakHashMap = new WeakHashMap();
        weakHashMap.put(1,2);
    }
}
