package com.baohongfei.tij.tij4.concurrency.p06;

//{ThrowsException}
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NaiveExceptionHandling
{
	public static void main(String[] args)
	{
		try
		{
			ExecutorService exec = Executors.newCachedThreadPool();
			exec.execute(new ExceptionThread());
		} catch (RuntimeException ue)
		{
			// This statement will NOT execute!
			System.out.println("Exception has been handled!");
		}
	}
}