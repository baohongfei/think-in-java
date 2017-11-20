package com.baohongfei.tij.tij4.concurrency.p02;

import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.baohongfei.tij.tij4.net.mindview.util.Generator;

public class E04_Fibonacci2
{
	public static void main(String[] args)
	{
		ExecutorService exec = Executors.newCachedThreadPool();
		for (int i = 1; i <= 5; i++)
			exec.execute(new Fibonacci(i));
		Thread.yield();
		exec.shutdown();
		exec = Executors.newFixedThreadPool(5);
		for (int i = 1; i <= 5; i++)
			exec.execute(new Fibonacci(i));
		Thread.yield();
		exec.shutdown();
		exec = Executors.newSingleThreadExecutor();
		for (int i = 1; i <= 5; i++)
			exec.execute(new Fibonacci(i));
		Thread.yield();
		exec.shutdown();
	}
}

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
