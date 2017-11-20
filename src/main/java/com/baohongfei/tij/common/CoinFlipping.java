package com.baohongfei.tij.common;

/**
 * Created by hofer on 03/07/17.
 */
public class CoinFlipping {

    public static void main(String[] args) {

        int positive = 0;
        int negative = 0;

        for (int i = 0; i < 100000000; i++) {
            if (coinFlip()) {
                positive++;
            } else {
                negative++;
            }
        }

        System.out.println(positive/negative);

        System.out.println("My Decision is:"+coinFlip());




    }

    private static boolean coinFlip(){
        double coinValue = Math.random();
        return coinValue < 0.5?true:false;
    }

}
