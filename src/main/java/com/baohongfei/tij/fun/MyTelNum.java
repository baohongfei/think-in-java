package com.baohongfei.tij.fun;

/**
 * Created by terry on 6/25/16.
 */
public class MyTelNum {
    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 4, 5, 6, 8, 9};
        int[] index = new int[]{0, 5, 4, 1, 4, 2, 2, 6, 5, 3, 5};
        String tel = "";
        for (int i : index) {
            tel += arr[i];
        }
        System.out.println("联系方式:" + tel);
    }
}
