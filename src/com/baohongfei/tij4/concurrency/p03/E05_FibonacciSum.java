package com.baohongfei.tij4.concurrency.p03;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.baohongfei.tij4.net.mindview.util.Generator;

/******************
 * Exercise 5 ***************** Modify Exercise 2 so that the task is a Callable
 * that sums the values of all the Fibonacci numbers. Create several tasks and
 * display the results.
 ***********************************************/
class FibonacciSum implements Generator<Integer>, Callable<Integer>
{
	private int count;
	private final int n;

	public FibonacciSum(int n)
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
	public Integer call()
	{
		int sum = 0;
		for (int i = 0; i < n; i++)
		{
			sum += next();
		}
		return sum;
	}
}

public class E05_FibonacciSum
{
	public static void main(String[] args)
	{
		ExecutorService exec = Executors.newCachedThreadPool();
		ArrayList<Future<Integer>> results = new ArrayList<Future<Integer>>();
		for (int i = 1; i <= 5; i++)
		{
			results.add(exec.submit(new FibonacciSum(i)));
		}
		Thread.yield();
		exec.shutdown();
		for (Future<Integer> fi : results)
			try
			{
				System.out.println(fi.get());
			} catch (Exception e)
			{
				e.printStackTrace();
			}
	}
}
