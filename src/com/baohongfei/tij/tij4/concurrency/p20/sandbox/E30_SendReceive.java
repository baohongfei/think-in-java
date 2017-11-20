package com.baohongfei.tij.tij4.concurrency.p20.sandbox;

/********************** Exercise 30 ***********************
 * Modify PipedIO.java to use a BlockingQueue instead of
 * a pipe.
 *********************************************************/
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

class CharQueue extends LinkedBlockingQueue<Character>
{
}

class Sender implements Runnable
{
    private Random rand = new Random(47);
    private CharQueue out = new CharQueue();

    public CharQueue getQueue()
    {
        return out;
    }

    @Override
    public void run()
    {
        try
        {
            while (true)
                for (char c = 'A'; c <= 'z'; c++)
                {
                    out.put(c);
                    TimeUnit.MILLISECONDS.sleep(rand.nextInt(500));
                }
        } catch (InterruptedException e)
        {
            System.out.println(e + " Sender interrupted");
        }
    }
}

class Receiver implements Runnable
{
    private CharQueue in;

    public Receiver(Sender sender)
    {
        in = sender.getQueue();
    }

    @Override
    public void run()
    {
        try
        {
            while (true)
            {
                // Blocks until characters are there:
                System.out.print("Read: " + in.take() + ", ");
            }
        } catch (InterruptedException e)
        {
            System.out.println(e + " Reader interrupted");
        }
    }
}

public class E30_SendReceive
{
    public static void main(String[] args) throws Exception
    {
        Sender sender = new Sender();
        Receiver receiver = new Receiver(sender);
        ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(sender);
        exec.execute(receiver);
        TimeUnit.SECONDS.sleep(4);
        exec.shutdownNow();
    }
}
