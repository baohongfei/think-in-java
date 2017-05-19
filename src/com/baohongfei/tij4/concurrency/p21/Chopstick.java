package com.baohongfei.tij4.concurrency.p21;

//Chopsticks for dining philosophers.

public class Chopstick
{
    private boolean taken = false;

    public synchronized void take() throws InterruptedException
    {
        while (taken)
        {
            wait();
        }
        taken = true;
    }

    public synchronized void drop()
    {
        taken = false;
        notifyAll();
    }
}
