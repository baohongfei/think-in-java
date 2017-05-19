package com.baohongfei.util;

import java.util.concurrent.TimeUnit;

/**
 * GetRunTime
 *
 * @author BaoHongfei
 * @since 2015年5月11日 下午4:40:49
 */
public class GetRunTime {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        long end = System.currentTimeMillis();

        System.out.println("-------------------------------------------------------------------------");
        System.out.println("" + (end - start) / 1000 + " seconds passed during the execution.");
        System.out.println("-------------------------------------------------------------------------");
    }
}
