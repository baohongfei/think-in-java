package com.baohongfei.tij.jdk8.p01lamda;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author hofer.bhf
 * created on 2017/11/27 10:40 PM
 */
public class C05StringComparator {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("zhangsan", "lisi", "wangwu", "zhaoliu");

        Collections.sort(names, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });
        System.out.println(names);

        // expression o1.compareTo(o2)
        // statement {return o1.compareTo(o2);}
        Collections.sort(names, (o1, o2) -> o1.compareTo(o2));
        System.out.println(names);

        Collections.sort(names, Comparator.naturalOrder());
        System.out.println(names);

        Collections.sort(names, String::compareTo);
        System.out.println(names);

        Collections.sort(names, Comparator.reverseOrder());
        System.out.println(names);

    }


}
