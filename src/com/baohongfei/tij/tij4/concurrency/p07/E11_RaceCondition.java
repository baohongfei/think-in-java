package com.baohongfei.tij.tij4.concurrency.p07;

//{RunByHand}
/****************** Exercise 11 *****************
 * Create a class containing two data fields, and a
 * method that manipulates those fields in a multistep
 * process so that, during the execution of that method,
 * those fields are in an "improper state" (according to
 * some definition that you establish). Add methods to
 * read the fields, and create multiple threads to call
 * the various methods and show that the data is visible
 * in its "improper state." Fix the problem using the
 * synchronized keyword.
 ***********************************************/
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
//The conditions, which always must hold are the following:
//1. If the tank is EMPTY then the current_load == 0
//2. If the tank is LOADED then the current_load >= 0

class Tank
{
	enum State
	{
		EMPTY, LOADED
	};

	private State state = State.EMPTY;
	private int current_load = 0;

	public void validate()
	{
		if ((state == State.EMPTY && current_load != 0)
				|| (state == State.LOADED && current_load == 0))
		{
			throw new IllegalStateException();
		}
	}

	public void fill()
	{
		state = State.LOADED;
		Thread.yield(); // Cause failure faster
		current_load = 10; // Arbitrary value
	}

	public void drain()
	{
		state = State.EMPTY;
		Thread.yield();
		current_load = 0;
	}
}

class ConsistencyChecker implements Runnable
{
	private static Random rnd = new Random();
	private Tank tank;

	ConsistencyChecker(Tank tank)
	{
		this.tank = tank;
	}

	@Override
	public void run()
	{
		for (;;)
		{
			// Decide whether to fill or drain the tank
			if (rnd.nextBoolean())
			{
				tank.fill();
			} else
			{
				tank.drain();
			}
			tank.validate();
		}
	}
}

class MyUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler
{
	@Override
	public void uncaughtException(Thread t, Throwable e)
	{
		System.out.println("caught " + e);
	}
}

public class E11_RaceCondition
{
	public static void main(String[] args)
	{
		Thread.setDefaultUncaughtExceptionHandler(new MyUncaughtExceptionHandler());
		System.out.println("Press Control-C to exit");
		ExecutorService exec = Executors.newCachedThreadPool();
		Tank tank = new Tank();
		for (int i = 0; i < 10; i++)
		{
			exec.execute(new ConsistencyChecker(tank));
		}
		Thread.yield();
		exec.shutdown();
	}
}
