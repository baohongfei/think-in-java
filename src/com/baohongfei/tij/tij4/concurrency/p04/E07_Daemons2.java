package com.baohongfei.tij.tij4.concurrency.p04;

/****************** Exercise 7 *****************
 * Experiment with different sleep times in
 * Daemons.java to see what happens.
 ***********************************************/

import java.util.concurrent.TimeUnit;

import com.baohongfei.tij.tij4.net.mindview.util.Print;

class Daemon2 implements Runnable
{
	private Thread[] t = new Thread[10];

	@Override
	public void run()
	{
		for (int i = 0; i < t.length; i++)
		{
			t[i] = new Thread(new DaemonSpawn());
			t[i].start();
			Print.printnb("DaemonSpawn " + i + " started, ");
		}
		try
		{
			// To better see the effect of altering main
			// application thread's sleep time.
			TimeUnit.MILLISECONDS.sleep(100);
		} catch (InterruptedException e)
		{ /* Ignore */
		}
		for (int i = 0; i < t.length; i++)
		{
			Print.printnb("t[" + i + "].isDaemon() = " + t[i].isDaemon() + ", ");
		}
		while (true)
		{
			Thread.yield();
		}
	}
}

/*
 * Our program accepts the sleep time (in milliseconds) as a command line
 * argument. By providing different values (usually much less than 1 second),
 * you see that the chief daemon thread (running the Daemon2 task) abruptly
 * terminates before even reaching the endless loop.
 */
public class E07_Daemons2
{
	public static void main(String[] args) throws Exception
	{
		if (args.length != 1)
		{
			System.err.println("Sleep time needs to be provided in msecs");
			return;
		}
		int sleep_time = Integer.parseInt(args[0]);
		Thread d = new Thread(new Daemon2());
		d.setDaemon(true);
		d.start();
		Print.printnb("d.isDaemon() = " + d.isDaemon() + ", ");
		TimeUnit.MILLISECONDS.sleep(sleep_time);
	}
}
