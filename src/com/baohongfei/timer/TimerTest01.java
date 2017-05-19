package com.baohongfei.timer;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 
 * TimerTest01
 *
 * @author BaoHongfei
 * @since 2015年1月29日 上午10:37:47
 * @see http://www.cnblogs.com/chenssy/p/3788407.html
 */
public class TimerTest01
{
    Timer timer;

    public TimerTest01(int time)
    {
        timer = new Timer();
        timer.schedule(new TimerTaskTest01(), time * 1000);
    }

    public static void main(String[] args)
    {
        System.out.println("timer begin....");
        new TimerTest01(3);
    }
}

class TimerTaskTest01 extends TimerTask
{

    @Override
    public void run()
    {
        System.out.println("Time's up!!!!");
    }
}
