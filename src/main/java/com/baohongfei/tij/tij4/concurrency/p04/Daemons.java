package com.baohongfei.tij.tij4.concurrency.p04;

import java.util.concurrent.TimeUnit;

import com.baohongfei.tij.tij4.net.mindview.util.Print;

class Daemon implements Runnable
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

class DaemonSpawn implements Runnable
{
	@Override
	public void run()
	{
		while (true)
		{
			Thread.yield();
		}
	}
}

public class Daemons
{
	public static void main(String[] args) throws Exception
	{
		Thread d = new Thread(new Daemon());
		d.setDaemon(true);
		d.start();
		Print.printnb("d.isDaemon() = " + d.isDaemon() + ", ");
		// Allow the daemon threads to
		// finish their startup processes:
		TimeUnit.SECONDS.sleep(1);
	}
}
