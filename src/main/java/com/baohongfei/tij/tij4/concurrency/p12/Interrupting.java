package com.baohongfei.tij.tij4.concurrency.p12;

//Interrupting a blocked thread.
import static com.baohongfei.tij.tij4.net.mindview.util.Print.print;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import com.baohongfei.tij.tij4.net.mindview.util.Print;

class SleepBlocked implements Runnable
{
    @Override
    public void run()
    {
        try
        {
            TimeUnit.SECONDS.sleep(100);
        } catch (InterruptedException e)
        {
            Print.print("InterruptedException");
        }
        Print.print("Exiting SleepBlocked.run()");
    }
}

class IOBlocked implements Runnable
{
    private InputStream in;

    public IOBlocked(InputStream is)
    {
        in = is;
    }

    @Override
    public void run()
    {
        try
        {
            Print.print("Waiting for read():");
            in.read();
        } catch (IOException e)
        {
            if (Thread.currentThread().isInterrupted())
            {
                Print.print("Interrupted from blocked I/O");
            } else
            {
                throw new RuntimeException(e);
            }
        }
        Print.print("Exiting IOBlocked.run()");
    }
}

class SynchronizedBlocked implements Runnable
{
    public synchronized void f()
    {
        while (true)
        {
            // Never releases lock
            Thread.yield();
        }
    }

    public SynchronizedBlocked()
    {
        new Thread()
        {
            @Override
            public void run()
            {
                f(); // Lock acquired by this thread
            }
        }.start();
    }

    @Override
    public void run()
    {
        Print.print("Trying to call f()");
        f();
        Print.print("Exiting SynchronizedBlocked.run()");
    }
}

public class Interrupting
{
    private static ExecutorService exec = Executors.newCachedThreadPool();

    static void test(Runnable r) throws InterruptedException
    {
        Future<?> f = exec.submit(r);
        TimeUnit.MILLISECONDS.sleep(100);
        Print.print("Interrupting " + r.getClass().getName());
        f.cancel(true); // Interrupts if running
        Print.print("Interrupt sent to " + r.getClass().getName());
    }

    public static void main(String[] args) throws Exception
    {
        test(new SleepBlocked());
        test(new IOBlocked(System.in));
        test(new SynchronizedBlocked());
        TimeUnit.SECONDS.sleep(3);
        Print.print("Aborting with System.exit(0)");
        System.exit(0); // ... since last 2 interrupts failed
    }
}
