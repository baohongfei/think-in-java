package com.baohongfei.tij4.concurrency.p10;

import static com.baohongfei.tij4.net.mindview.util.Print.print;

class DualSynch
{
    private Object syncObject = new Object();

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
        synchronized (syncObject)
        {
            for (int i = 0; i < 5; i++)
            {
                print("g()");
                Thread.yield();
            }
        }
    }
}

public class SyncObject
{
    public static void main(String[] args)
    {
        final DualSynch ds = new DualSynch();
        new Thread()
        {
            @Override
            public void run()
            {
                ds.f();
            }
        }.start();
        ds.g();
    }
}
