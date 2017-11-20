package com.baohongfei.tij.tij4.concurrency.p16;

//{Args: 1 200}
/********************** Exercise 24 ***********************
 * Solve a single-producer, single-consumer problem using
 * wait() and notifyAll(). The producer must not overflow
 * the receiver’s buffer, which can happen if the producer
 Concurrency 633
 * is faster than the consumer. If the consumer is faster
 * than the producer, then it must not read the same data
 * more than once. Do not assume anything about the relative
 * speeds of the producer or consumer.
 *********************************************************/
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

//A queue for solving flow-control problems.
class FlowQueue<T>
{
    private Queue<T> queue = new LinkedList<T>();
    private int maxSize;

    public FlowQueue(int maxSize)
    {
        this.maxSize = maxSize;
    }

    public synchronized void put(T v) throws InterruptedException
    {
        while (queue.size() >= maxSize)
        {
            wait();
        }
        queue.offer(v);
        maxSize++;
        notifyAll();
    }

    public synchronized T get() throws InterruptedException
    {
        while (queue.isEmpty())
        {
            wait();
        }
        T returnVal = queue.poll();
        maxSize--;
        notifyAll();
        return returnVal;
    }
}

class Item
{
    private static int counter;
    private int id = counter++;

    @Override
    public String toString()
    {
        return "Item " + id;
    }
}

// Produces Item objects
class Producer implements Runnable
{
    private int delay;
    private FlowQueue<Item> output;

    public Producer(FlowQueue<Item> output, int sleepTime)
    {
        this.output = output;
        delay = sleepTime;
    }

    @Override
    public void run()
    {
        for (;;)
        {
            try
            {
                output.put(new Item());
                TimeUnit.MILLISECONDS.sleep(delay);
            } catch (InterruptedException e)
            {
                return;
            }
        }
    }
}

// Consumes any object
class Consumer implements Runnable
{
    private int delay;
    private FlowQueue<?> input;

    public Consumer(FlowQueue<?> input, int sleepTime)
    {
        this.input = input;
        delay = sleepTime;
    }

    @Override
    public void run()
    {
        for (;;)
        {
            try
            {
                System.out.println(input.get());
                TimeUnit.MILLISECONDS.sleep(delay);
            } catch (InterruptedException e)
            {
                return;
            }
        }
    }
}

public class E24_ProducerConsumer
{
    public static void main(String[] args) throws Exception
    {
        if (args.length < 2)
        {
            System.err.println("Usage java E24_ProducerConsumer"
                    + " <producer sleep time> <consumer sleep time>");
            System.exit(1);
        }
        int producerSleep = Integer.parseInt(args[0]);
        int consumerSleep = Integer.parseInt(args[1]);
        FlowQueue<Item> fq = new FlowQueue<Item>(100);
        ExecutorService exec = Executors.newFixedThreadPool(2);
        exec.execute(new Producer(fq, producerSleep));
        exec.execute(new Consumer(fq, consumerSleep));
        TimeUnit.SECONDS.sleep(2);
        exec.shutdownNow();
    }
}
