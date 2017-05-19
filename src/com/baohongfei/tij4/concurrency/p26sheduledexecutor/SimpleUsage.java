package com.baohongfei.tij4.concurrency.p26sheduledexecutor;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * SimpleUsage
 *
 * @author BaoHongfei
 * @since 2015年1月16日 上午10:26:02
 */
public class SimpleUsage
{
    public static void main(String[] args)
    {
        ScheduledThreadPoolExecutor scheduler = new ScheduledThreadPoolExecutor(
                10);
        Runnable event = new Runnable()
        {

            @Override
            public void run()
            {
                System.out.println("吃饭，睡觉，打豆豆");

            }
        };
        scheduler.schedule(event, 1, TimeUnit.SECONDS);
        scheduler.scheduleAtFixedRate(event, 5, 1, TimeUnit.SECONDS);
    }
}
