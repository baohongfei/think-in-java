package com.baohongfei.tij.tij4.concurrency.p10;

import static com.baohongfei.tij.tij4.net.mindview.util.Print.print;

/******************
 * Exercise 15 ************************ Create a class with three methods
 * containing critical sections that all synchronize on the same object. Create
 * multiple tasks to demonstrate that only one of these methods can run at a
 * time. Now modify the methods so that each one synchronizes on a different
 * object and Concurrency 615 show that all three methods can be running at
 * once.
 ******************************************************/
class SingleSynch
{
    public synchronized void f()
    {
        for (int i = 0; i < 5; i++)
        {
            print("f()");
            Thread.yield();
        }
    }

    public synchronized void g()
    {
        for (int i = 0; i < 5; i++)
        {
            print("g()");
            Thread.yield();
        }
    }

    public synchronized void h()
    {
        for (int i = 0; i < 5; i++)
        {
            print("h()");
            Thread.yield();
        }
    }
}

class TripleSynch
{
    private Object syncObjectG = new Object();
    private Object syncObjectH = new Object();

    public synchronized void f()
    {
        for (int i = 0; i < 5; i++)
        {
            print("f()");
            Thread.yield();
        }
    }

    public void g()
    {
        synchronized (syncObjectG)
        {
            for (int i = 0; i < 5; i++)
            {
                print("g()");
                Thread.yield();
            }
        }
    }

    public void h()
    {
        synchronized (syncObjectH)
        {
            for (int i = 0; i < 5; i++)
            {
                print("h()");
                Thread.yield();
            }
        }
    }
}

public class E15_SyncObject
{
    public static void main(String[] args) throws Exception
    {
        final SingleSynch singleSync = new SingleSynch();
        final TripleSynch tripleSync = new TripleSynch();
        print("Test SingleSynch...");
        Thread t1 = new Thread()
        {
            @Override
            public void run()
            {
                singleSync.f();
            }
        };
        t1.start();
        Thread t2 = new Thread()
        {
            @Override
            public void run()
            {
                singleSync.g();
            }
        };
        t2.start();
        singleSync.h();
        t1.join(); // Wait for t1 to finish its work
        t2.join(); // Wait for t2 to finish its work
        print("Test TripleSynch...");
        new Thread()
        {
            @Override
            public void run()
            {
                tripleSync.f();
            }
        }.start();
        new Thread()
        {
            @Override
            public void run()
            {
                tripleSync.g();
            }
        }.start();
        tripleSync.h();
    }
}
