package com.baohongfei.tij.tij4.concurrency.p04;

import static com.baohongfei.tij.tij4.net.mindview.util.Print.print;

import java.util.concurrent.TimeUnit;

class ADaemon implements Runnable
{
	@Override
	public void run()
	{
		try
		{
			print("Starting ADaemon");
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e)
		{
			print("Exiting via InterruptedException");
		} finally
		{
			print("This should always run?");
		}
	}
}

public class DaemonsDontRunFinally
{
	public static void main(String[] args) throws Exception
	{
		Thread t = new Thread(new ADaemon());
		t.setDaemon(true);
		t.start();
		TimeUnit.MILLISECONDS.sleep(500);
	}
}
