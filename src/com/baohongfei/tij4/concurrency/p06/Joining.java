package com.baohongfei.tij4.concurrency.p06;

//Understanding join().
import static com.baohongfei.tij4.net.mindview.util.Print.print;

class Sleeper extends Thread
{
	private int duration;

	public Sleeper(String name, int sleepTime)
	{
		super(name);
		duration = sleepTime;
		start();
	}

	@Override
	public void run()
	{
		try
		{
			sleep(duration);
		} catch (InterruptedException e)
		{
			print(getName() + " was interrupted. " + "isInterrupted(): "
					+ isInterrupted());
			return;
		}
		print(getName() + " has awakened");
	}
}

class Joiner extends Thread
{
	private Sleeper sleeper;

	public Joiner(String name, Sleeper sleeper)
	{
		super(name);
		this.sleeper = sleeper;
		start();
	}

	@Override
	public void run()
	{
		try
		{
			sleeper.join();
		} catch (InterruptedException e)
		{
			print("Interrupted");
		}
		print(getName() + " join completed");
	}
}

public class Joining
{
	public static void main(String[] args)
	{
		Sleeper sleepy = new Sleeper("Sleepy", 1500), grumpy = new Sleeper(
				"Grumpy", 1500);
		Joiner dopey = new Joiner("Dopey", sleepy), doc = new Joiner("Doc",
				grumpy);
		grumpy.interrupt();
	}
}
