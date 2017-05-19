package com.baohongfei.tij4.concurrency.p28exchanger;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.baohongfei.tij4.concurrency.p27semaphore.Fat;
import com.baohongfei.tij4.net.mindview.util.BasicGenerator;
import com.baohongfei.tij4.net.mindview.util.Generator;

class ExchangerProducer<T> implements Runnable
{
    private Generator<T> generator;
    private Exchanger<List<T>> exchanger;
    private List<T> holder;

    ExchangerProducer(Exchanger<List<T>> exchg, Generator<T> gen, List<T> holder)
    {
        exchanger = exchg;
        generator = gen;
        this.holder = holder;
    }

    @Override
    public void run()
    {
        try
        {
            while (!Thread.interrupted())
            {
                for (int i = 0; i < ExchangerDemo.size; i++)
                    holder.add(generator.next());
                // Exchange full for empty:
                holder = exchanger.exchange(holder);
            }
        } catch (InterruptedException e)
        {
            // OK to terminate this way.
        }
    }
}

class ExchangerConsumer<T> implements Runnable
{
    private Exchanger<List<T>> exchanger;
    private List<T> holder;
    private volatile T value;

    ExchangerConsumer(Exchanger<List<T>> ex, List<T> holder)
    {
        exchanger = ex;
        this.holder = holder;
    }

    @Override
    public void run()
    {
        try
        {
            while (!Thread.interrupted())
            {
                holder = exchanger.exchange(holder);
                for (T x : holder)
                {
                    value = x; // Fetch out value
                    holder.remove(x); // OK for CopyOnWriteArrayList
                }
            }
        } catch (InterruptedException e)
        {
            // OK to terminate this way.
        }
        System.out.println("Final value: " + value);
    }
}

public class ExchangerDemo
{
    static int size = 10;
    static int delay = 5; // Seconds

    public static void main(String[] args) throws Exception
    {
        if (args.length > 0)
        {
            size = new Integer(args[0]);
        }
        if (args.length > 1)
        {
            delay = new Integer(args[1]);
        }
        ExecutorService exec = Executors.newCachedThreadPool();
        Exchanger<List<Fat>> xc = new Exchanger<List<Fat>>();
        List<Fat> producerList = new CopyOnWriteArrayList<Fat>(), consumerList = new CopyOnWriteArrayList<Fat>();
        exec.execute(new ExchangerProducer<Fat>(xc, BasicGenerator
                .create(Fat.class), producerList));
        exec.execute(new ExchangerConsumer<Fat>(xc, consumerList));
        TimeUnit.SECONDS.sleep(delay);
        exec.shutdownNow();
    }
}
