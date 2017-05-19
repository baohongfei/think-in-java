package com.baohongfei.tij4.concurrency.p09;

//{Args: 100}
/****************** Exercise 14 ************************
 * Demonstrate that java.util.Timer scales to large numbers
 * by creating a program that generates many Timer objects
 * that perform some simple task when the timeout completes.
 ******************************************************/
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class E14_ManyTimers
{
	public static void main(String[] args) throws Exception
	{
		if (args.length < 1)
		{
			System.err.println("Usage: java E14_ManyTimers <num of timers>");
		}
		int numOfTimers = Integer.parseInt(args[0]);
		for (int i = 0; i < numOfTimers; i++)
		{
			new Timer().schedule(new TimerTask()
			{
				@Override
				public void run()
				{
					System.out.println(System.currentTimeMillis());
				}
			}, numOfTimers - i);
		}
		// Wait for timers to expire
		TimeUnit.MILLISECONDS.sleep(2 * numOfTimers);
		System.exit(0);
	}
}
