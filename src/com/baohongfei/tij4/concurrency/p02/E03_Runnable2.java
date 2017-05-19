package com.baohongfei.tij4.concurrency.p02;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*The call to shutdown( ) is an asynchronous method call to initiate an orderly
 shutdown procedure. The JDK states: “After being shut down, the executor will
 eventually terminate, at which point no tasks are actively executing, no tasks
 are awaiting execution, and no new tasks can be submitted.”*/

public class E03_Runnable2
{
	public static void main(String[] args)
	{
		ExecutorService exec = Executors.newCachedThreadPool();
		for (int i = 0; i < 5; i++)
			exec.execute(new Printer());
		Thread.yield();
		exec.shutdown();
		exec = Executors.newFixedThreadPool(5);
		for (int i = 0; i < 5; i++)
			exec.execute(new Printer());
		Thread.yield();
		exec.shutdown();
		exec = Executors.newSingleThreadExecutor();
		for (int i = 0; i < 5; i++)
			exec.execute(new Printer());
		Thread.yield();
		exec.shutdown();
	}
}

class Printer implements Runnable
{
	private static int taskCount;
	private final int id = taskCount++;

	Printer()
	{
		System.out.println("Printer started, ID = " + id);
	}

	@Override
	public void run()
	{
		System.out.println("Stage 1..., ID = " + id);
		Thread.yield();
		System.out.println("Stage 2..., ID = " + id);
		Thread.yield();
		System.out.println("Stage 3..., ID = " + id);
		Thread.yield();
		System.out.println("Printer ended, ID = " + id);
	}
}
