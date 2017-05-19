package com.baohongfei.tij4.concurrency.p04;

import static com.baohongfei.tij4.net.mindview.util.Print.printnb;

import java.util.concurrent.TimeUnit;

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
			printnb("DaemonSpawn " + i + " started, ");
		}
		for (int i = 0; i < t.length; i++)
		{
			printnb("t[" + i + "].isDaemon() = " + t[i].isDaemon() + ", ");
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
		printnb("d.isDaemon() = " + d.isDaemon() + ", ");
		// Allow the daemon threads to
		// finish their startup processes:
		TimeUnit.SECONDS.sleep(1);
	}
}
