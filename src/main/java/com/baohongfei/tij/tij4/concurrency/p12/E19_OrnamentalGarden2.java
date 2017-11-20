package com.baohongfei.tij.tij4.concurrency.p12;

/****************** Exercise 19 ************************
 * Modify OrnamentalGarden.java so that it uses
 * interrupt().
 ******************************************************/
import static com.baohongfei.tij.tij4.net.mindview.util.Print.print;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Count
{
    private int count = 0;
    private Random rand = new Random(47);

    // Remove the synchronized keyword to see counting fail:
    public synchronized int increment()
    {
        int temp = count;
        if (rand.nextBoolean()) // Yield half the time
        {
            Thread.yield();
        }
        return (count = ++temp);
    }

    public synchronized int value()
    {
        return count;
    }
}

class Entrance2 implements Runnable
{
    private static Count count = new Count();
    private static List<Entrance2> entrances = new ArrayList<Entrance2>();
    private int number;
    private final int id;

    public Entrance2(int id)
    {
        this.id = id;
        entrances.add(this);
    }

    @Override
    public void run()
    {
        for (;;)
        {
            synchronized (this)
            {
                ++number;
            }
            print(this + " Total: " + count.increment());
            try
            {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e)
            {
                print("Stopping " + this);
                return;
            }
        }
    }

    public synchronized int getValue()
    {
        return number;
    }

    @Override
    public String toString()
    {
        return "Entrance " + id + ": " + getValue();
    }

    public static int getTotalCount()
    {
        return count.value();
    }

    public static int sumEntrances()
    {
        int sum = 0;
        for (Entrance2 entrance : entrances)
        {
            sum += entrance.getValue();
        }
        return sum;
    }
}

/*
 * Notice how much neater this program is with the built-in thread interruption
 * facility.
 */
public class E19_OrnamentalGarden2
{
    public static void main(String[] args) throws Exception
    {
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++)
        {
            exec.execute(new Entrance2(i));
        }
        TimeUnit.SECONDS.sleep(3);
        exec.shutdownNow();
        if (!exec.awaitTermination(250, TimeUnit.MILLISECONDS))
        {
            print("Some tasks were not terminated!");
        }
        print("Total: " + Entrance2.getTotalCount());
        print("Sum of Entrances: " + Entrance2.sumEntrances());
    }
}
