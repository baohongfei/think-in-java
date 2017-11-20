package com.baohongfei.tij.timer;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledExecutorTest01
{
    private ScheduledExecutorService scheduExec;

    public long start;

    ScheduledExecutorTest01()
    {
        this.scheduExec = Executors.newScheduledThreadPool(2);
        this.start = System.currentTimeMillis();
    }

    public void timerOne()
    {
        scheduExec.schedule(new Runnable()
        {
            @Override
            public void run()
            {
                System.out.println("timerOne,the time:"
                        + (System.currentTimeMillis() - start));
                try
                {
                    Thread.sleep(4000);
                } catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
        }, 1000, TimeUnit.MILLISECONDS);
    }

    public void timerTwo()
    {
        scheduExec.schedule(new Runnable()
        {
            @Override
            public void run()
            {
                System.out.println("timerTwo,the time:"
                        + (System.currentTimeMillis() - start));
            }
        }, 2000, TimeUnit.MILLISECONDS);
    }

    public static void main(String[] args)
    {
        ScheduledExecutorTest01 test = new ScheduledExecutorTest01();
        test.timerOne();
        test.timerTwo();
    }
}
