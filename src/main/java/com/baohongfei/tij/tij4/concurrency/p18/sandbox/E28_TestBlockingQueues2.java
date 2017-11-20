package com.baohongfei.tij.tij4.concurrency.p18.sandbox;

//{RunByHand}
/********************** Exercise 28 ***********************
 * Modify TestBlockingQueues.java by adding a new task that
 * places LiftOff on the BlockingQueue, instead of doing it
 * in main().
 *********************************************************/
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;

import com.baohongfei.tij.tij4.concurrency.p01.LiftOff;

class LiftOffRunner implements Runnable
{
    private BlockingQueue<LiftOff> rockets;

    public LiftOffRunner(BlockingQueue<LiftOff> queue)
    {
        rockets = queue;
    }

    public void add(LiftOff lo) throws InterruptedException
    {
        rockets.put(lo);
    }

    @Override
    public void run()
    {
        try
        {
            while (!Thread.interrupted())
            {
                LiftOff rocket = rockets.take();
                rocket.run(); // Use this thread
            }
        } catch (InterruptedException e)
        {
            System.out.print("Waking from take()");
        }
        System.out.print("Exiting LiftOffRunner");
    }
}

class LiftOffProducer implements Runnable
{
    private LiftOffRunner runner;

    public LiftOffProducer(LiftOffRunner runner)
    {
        this.runner = runner;
    }

    @Override
    public void run()
    {
        try
        {
            for (int i = 0; i < 5; i++)
                runner.add(new LiftOff(5));
        } catch (InterruptedException e)
        {
            System.out.print("Waking from put()");
        }
        System.out.print("Exiting LiftOffProducer");
    }
}

public class E28_TestBlockingQueues2
{
    private static void getkey()
    {
        try
        {
            new BufferedReader(new InputStreamReader(System.in)).readLine();
        } catch (java.io.IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    private static void getkey(String message)
    {
        System.out.print(message);
        getkey();
    }

    private static void test(String msg, BlockingQueue<LiftOff> queue)
    {
        ExecutorService exec = Executors.newFixedThreadPool(2);
        System.out.print(msg);
        LiftOffRunner runner = new LiftOffRunner(queue);
        LiftOffProducer producer = new LiftOffProducer(runner);
        exec.execute(runner);
        exec.execute(producer);
        getkey("Press 'ENTER' (" + msg + ")");
        exec.shutdownNow();
        System.out.print("Finished " + msg + " test");
    }

    public static void main(String[] args)
    {
        test("LinkedBlockingQueue", // Unlimited size
                new LinkedBlockingQueue<LiftOff>());
        test("ArrayBlockingQueue", // Fixed size
                new ArrayBlockingQueue<LiftOff>(3));
        test("SynchronousQueue", // Size of 1
                new SynchronousQueue<LiftOff>());
    }
}
