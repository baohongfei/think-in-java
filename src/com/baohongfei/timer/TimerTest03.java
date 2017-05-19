package com.baohongfei.timer;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class TimerTest03
{
    Timer timer;

    public TimerTest03()
    {
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTaskTest03(), 1000, 2000);
        // timer.schedule(new TimerTaskTest03(), 1000, 2000);
    }

    public static void main(String[] args)
    {
        new TimerTest03();
    }
}

class TimerTaskTest03 extends TimerTask
{

    @Override
    public void run()
    {
        Date date = new Date(this.scheduledExecutionTime());
        System.out.println("本次执行该线程的时间为：" + date + Thread.currentThread());
        try
        {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
