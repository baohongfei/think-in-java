package com.baohongfei.tij4.concurrency.p01;

/****************** Exercise 2 *****************
 * Following the form of generics/Fibonacci.java,
 * create a task that produces a sequence of n
 * Fibonacci numbers, where n is provided to the
 * constructor of the task. Create a number of these
 * tasks and drive them using threads.
 ***********************************************/
import java.util.Arrays;

import com.baohongfei.tij4.net.mindview.util.Generator;

class Fibonacci implements Generator<Integer>, Runnable
{
	private int count;
	private final int n;

	public Fibonacci(int n)
	{
		this.n = n;
	}

	@Override
	public Integer next()
	{
		return fib(count++);
	}

	private int fib(int n)
	{
		if (n < 2)
			return 1;
		return fib(n - 2) + fib(n - 1);
	}

	@Override
	public void run()
	{
		Integer[] sequence = new Integer[n];
		for (int i = 0; i < n; i++)
		{
			sequence[i] = next();
		}
		System.out.println("Seq. of " + n + ": " + Arrays.toString(sequence));
	}
}

public class E02_Fibonacci
{
	public static void main(String[] args)
	{
		for (int i = 1; i <= 5; i++)
			new Thread(new Fibonacci(i)).start();
	}
}
