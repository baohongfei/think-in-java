package com.baohongfei.tij.timer;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledExecutorTest02
{
    private ScheduledExecutorService scheduExec;

    public long start;

    ScheduledExecutorTest02()
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
                throw new RuntimeException();
            }
        }, 1000, TimeUnit.MILLISECONDS);
    }

    public void timerTwo()
    {
        scheduExec.scheduleAtFixedRate(new Runnable()
        {
            @Override
            public void run()
            {
                System.out.println("timerTwo invoked .....");
            }
        }, 2000, 500, TimeUnit.MILLISECONDS);
    }

    public static void main(String[] args)
    {
        ScheduledExecutorTest02 test = new ScheduledExecutorTest02();
        test.timerOne();
        test.timerTwo();
    }
}
