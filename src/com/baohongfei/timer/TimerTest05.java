package com.baohongfei.timer;

import java.util.Timer;
import java.util.TimerTask;

public class TimerTest05
{
    private Timer timer;

    public TimerTest05()
    {
        this.timer = new Timer();
    }

    public void timerOne()
    {
        timer.schedule(new TimerTask()
        {
            @Override
            public void run()
            {
                throw new RuntimeException();
            }
        }, 1000);
    }

    public void timerTwo()
    {
        timer.schedule(new TimerTask()
        {

            @Override
            public void run()
            {
                System.out.println("我会不会执行呢？？");
            }
        }, 1000);
    }

    public static void main(String[] args)
    {
        TimerTest05 test = new TimerTest05();
        test.timerOne();
        test.timerTwo();
    }
}
