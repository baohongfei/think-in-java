package com.baohongfei.fun;

/**
 * Created by terry on 6/25/16.
 */
public class MyTelNum {
    public static void main(String[] args) {
        int[] arr = new int[]{1,2,4,0,3,6,1};
        int[] index = new int[]{6,0,0,3,1,5,1,2,4,4,0};
        String tel = "";
        for (int i:index){
            tel += arr[i];
        }
        System.out.println("联系方式:"+tel);
    }
}
