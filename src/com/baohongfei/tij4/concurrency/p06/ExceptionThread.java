package com.baohongfei.tij4.concurrency.p06;

//{ThrowsException}
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExceptionThread implements Runnable
{
	@Override
	public void run()
	{
		throw new RuntimeException();
	}

	public static void main(String[] args)
	{
		ExecutorService exec = Executors.newCachedThreadPool();
		exec.execute(new ExceptionThread());
	}
}
