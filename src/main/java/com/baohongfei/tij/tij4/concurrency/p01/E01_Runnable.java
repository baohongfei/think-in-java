package com.baohongfei.tij.tij4.concurrency.p01;

/******************
 * Exercise 1 ***************** Implement a Runnable. Inside run(), print a
 * message, and then call yield(). Repeat this three times, and then return from
 * run(). Put a startup message in the constructor and a shutdown message when
 * the task terminates. Create a number of these tasks and drive them using
 * threads.
 ***********************************************/
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

public class E01_Runnable
{
	public static void main(String[] args)
	{
		for (int i = 0; i < 5; i++)
			new Thread(new Printer()).start();
	}
}
