package com.baohongfei.tij4.concurrency.p04;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/******************
 * Exercise 9 ***************** Modify SimplePriorities.java so that a custom
 * ThreadFactory sets the priorities of the threads.
 ***********************************************/
class SimplePriorities2 implements Runnable
{
	private int countDown = 5;
	private volatile double d; // No optimization

	@Override
	public String toString()
	{
		return Thread.currentThread() + ": " + countDown;
	}

	@Override
	public void run()
	{
		for (;;)
		{
			// An expensive, interruptable operation:
			for (int i = 1; i < 10000000; i++)
			{
				d += (Math.PI + Math.E) / i;
				if (i % 1000 == 0)
					Thread.yield();
			}
			System.out.println(this);
			if (--countDown == 0)
				return;
		}
	}
}

class PriorityThreadFactory implements ThreadFactory
{
	private final int priority;

	PriorityThreadFactory(int priority)
	{
		this.priority = priority;
	}

	@Override
	public Thread newThread(Runnable r)
	{
		Thread t = new Thread(r);
		t.setPriority(priority);
		return t;
	}
}

public class E09_SimplePrioritiesTF
{
	public static void main(String[] args)
	{
		ExecutorService exec = Executors
				.newCachedThreadPool(new PriorityThreadFactory(
						Thread.MIN_PRIORITY));
		for (int i = 0; i < 5; i++)
		{
			exec.execute(new SimplePriorities2());
		}
		Thread.yield();
		exec.shutdown();
		exec = Executors.newCachedThreadPool(new PriorityThreadFactory(
				Thread.MAX_PRIORITY));
		exec.execute(new SimplePriorities2());
		Thread.yield();
		exec.shutdown();
	}
}
