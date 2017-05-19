package com.baohongfei.tij4.concurrency.p19.sandbox;

/********************** Exercise 29 ***********************
 * Modify ToastOMatic.java to create peanut butter and jelly
 * on toast sandwiches using two separate assembly lines
 * (one for peanut butter, the second for jelly, then
 * merging the two lines).
 *********************************************************/
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

class Toast
{
    public enum Status
    {
        DRY, BUTTERED, JAMMED, READY
        {
            @Override
            public String toString()
            {
                return BUTTERED.toString() + " & " + JAMMED.toString();
            }
        }
    }

    private Status status = Status.DRY;
    private final int id;

    public Toast(int idn)
    {
        id = idn;
    }

    public void butter()
    {
        status = (status == Status.DRY) ? Status.BUTTERED : Status.READY;
    }

    public void jam()
    {
        status = (status == Status.DRY) ? Status.JAMMED : Status.READY;
    }

    public Status getStatus()
    {
        return status;
    }

    public int getId()
    {
        return id;
    }

    @Override
    public String toString()
    {
        return "Toast " + id + ": " + status;
    }
}

class ToastQueue extends LinkedBlockingQueue<Toast>
{
}

class Toaster implements Runnable
{
    private ToastQueue toastQueue;
    private int count;
    private Random rand = new Random(47);

    public Toaster(ToastQueue tq)
    {
        toastQueue = tq;
    }

    @Override
    public void run()
    {
        try
        {
            while (!Thread.interrupted())
            {
                TimeUnit.MILLISECONDS.sleep(100 + rand.nextInt(500));
                // Make toast
                Toast t = new Toast(count++);
                System.out.println(t);
                // Insert into queue
                toastQueue.put(t);
            }
        } catch (InterruptedException e)
        {
            System.out.println("Toaster interrupted");
        }
        System.out.println("Toaster off");
    }
}

// Apply butter to toast:
class Butterer implements Runnable
{
    private ToastQueue inQueue, butteredQueue;

    public Butterer(ToastQueue in, ToastQueue buttered)
    {
        inQueue = in;
        butteredQueue = buttered;
    }

    @Override
    public void run()
    {
        try
        {
            while (!Thread.interrupted())
            {
                // Blocks until next piece of toast is available:
                Toast t = inQueue.take();
                t.butter();
                System.out.println(t);
                butteredQueue.put(t);
            }
        } catch (InterruptedException e)
        {
            System.out.println("Butterer interrupted");
        }
        System.out.println("Butterer off");
    }
}

// Apply jam to toast:
class Jammer implements Runnable
{
    private ToastQueue inQueue, jammedQueue;

    public Jammer(ToastQueue in, ToastQueue jammed)
    {
        inQueue = in;
        jammedQueue = jammed;
    }

    @Override
    public void run()
    {
        try
        {
            while (!Thread.interrupted())
            {
                // Blocks until next piece of toast is available:
                Toast t = inQueue.take();
                t.jam();
                System.out.println(t);
                jammedQueue.put(t);
            }
        } catch (InterruptedException e)
        {
            System.out.println("Jammer interrupted");
        }
        System.out.println("Jammer off");
    }
}

// Consume the toast:
class Eater implements Runnable
{
    private ToastQueue finishedQueue;

    public Eater(ToastQueue finished)
    {
        finishedQueue = finished;
    }

    @Override
    public void run()
    {
        try
        {
            while (!Thread.interrupted())
            {
                // Blocks until next piece of toast is available:
                Toast t = finishedQueue.take();
                // Verify that all pieces are ready for consumption:
                if (t.getStatus() != Toast.Status.READY)
                {
                    System.out.println(">>>> Error: " + t);
                    System.exit(1);
                } else
                    System.out.println("Chomp! " + t);
            }
        } catch (InterruptedException e)
        {
            System.out.println("Eater interrupted");
        }
        System.out.println("Eater off");
    }
}

// Outputs alternate inputs on alternate channels:
class Alternator implements Runnable
{
    private ToastQueue inQueue, out1Queue, out2Queue;
    private boolean outTo2; // control alternation

    public Alternator(ToastQueue in, ToastQueue out1, ToastQueue out2)
    {
        inQueue = in;
        out1Queue = out1;
        out2Queue = out2;
    }

    @Override
    public void run()
    {
        try
        {
            while (!Thread.interrupted())
            {
                // Blocks until next piece of toast is available:
                Toast t = inQueue.take();
                if (!outTo2)
                    out1Queue.put(t);
                else
                    out2Queue.put(t);
                outTo2 = !outTo2; // change state for next time
            }
        } catch (InterruptedException e)
        {
            System.out.println("Alternator interrupted");
        }
        System.out.println("Alternator off");
    }
}

// Accepts toasts on either channel, and relays them on to
// a "single" successor
class Merger implements Runnable
{
    private ToastQueue in1Queue, in2Queue, toBeButteredQueue, toBeJammedQueue,
            finishedQueue;

    public Merger(ToastQueue in1, ToastQueue in2, ToastQueue toBeButtered,
            ToastQueue toBeJammed, ToastQueue finished)
    {
        in1Queue = in1;
        in2Queue = in2;
        toBeButteredQueue = toBeButtered;
        toBeJammedQueue = toBeJammed;
        finishedQueue = finished;
    }

    @Override
    public void run()
    {
        try
        {
            while (!Thread.interrupted())
            {
                // Blocks until next piece of toast is available:
                Toast t = null;
                while (t == null)
                {
                    t = in1Queue.poll(50, TimeUnit.MILLISECONDS);
                    if (t != null)
                        break;
                    t = in2Queue.poll(50, TimeUnit.MILLISECONDS);
                }
                // Relay toast onto the proper queue
                switch (t.getStatus())
                {
                case BUTTERED:
                    toBeJammedQueue.put(t);
                    break;
                case JAMMED:
                    toBeButteredQueue.put(t);
                    break;
                default:
                    finishedQueue.put(t);
                }
            }
        } catch (InterruptedException e)
        {
            System.out.println("Merger interrupted");
        }
        System.out.println("Merger off");
    }
}

/*
 * The finished sandwiches no longer come in predefined order because the two
 * assembly lines work in parallel. Our solution enhances the original program
 * from TIJ4 with an assembly line elaboration by Doug Lea
 * (http://gee.cs.oswego.edu/dl/cpj/assembly.html). Lea writes: “Polling
 * intrinsically relies on busy-wait loops, which are intrinsically wasteful
 * (but still sometimes less so than context-switching). Coping with this
 * requires empirically guided decisions about how to insert sleeps, yields, or
 * alternative actions to strike a balance between conserving CPU time and
 * maintaining acceptable average response latencies.” We must poll each queue
 * in the program with a wait time of 50 milliseconds (inside the Merger task),
 * because data could come from any queue, so blocking a specific one
 * indefinitely doesn’t work. The timed poll( ) method from the JDK needs no
 * busy-wait loop. We add only Alternator and Merger; Toaster, Butterer, Jammer
 * and Eater remain the same.
 */
public class E29_ToastOMatic2
{
    public static void main(String[] args) throws Exception
    {
        ToastQueue dryQueue = new ToastQueue(), butteredQueue = new ToastQueue(), toBeButteredQueue = new ToastQueue(), jammedQueue = new ToastQueue(), toBeJammedQueue = new ToastQueue(), finishedQueue = new ToastQueue();
        ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(new Toaster(dryQueue));
        exec.execute(new Alternator(dryQueue, toBeButteredQueue,
                toBeJammedQueue));
        exec.execute(new Butterer(toBeButteredQueue, butteredQueue));
        exec.execute(new Jammer(toBeJammedQueue, jammedQueue));
        exec.execute(new Merger(butteredQueue, jammedQueue, toBeButteredQueue,
                toBeJammedQueue, finishedQueue));
        exec.execute(new Eater(finishedQueue));
        TimeUnit.SECONDS.sleep(5);
        exec.shutdownNow();
    }
}
