package com.baohongfei.tij.jdk8.p01lamda;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

/**
 * 20/11/2017 by 鲍彻
 */
public class C02HelloWorld02 {

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3);

        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }

        for (Integer i : list) {
            System.out.println(i);
        }

        list.forEach(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) {
                System.out.println(integer);
            }
        });

    }
}
