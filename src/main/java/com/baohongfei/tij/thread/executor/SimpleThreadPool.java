package com.baohongfei.tij.thread.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * SimpleThreadPool
 *
 * @author BaoHongfei
 * @since 2014年12月23日 下午5:24:10
 */
public class SimpleThreadPool
{

	public static void main(String[] args)
	{
		ExecutorService executor = Executors.newFixedThreadPool(5);
		for (int i = 0; i < 10; i++)
		{
			Runnable worker = new WorkerThread("" + i);
			executor.execute(worker);
		}
		executor.shutdown();
		while (!executor.isTerminated())
		{
		}
		System.out.println("Finished all threads");
	}

}
