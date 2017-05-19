package com.baohongfei.tij4.concurrency.p02;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.baohongfei.tij4.concurrency.p01.LiftOff;

public class FixedThreadPool
{
	public static void main(String[] args)
	{
		// Constructor argument is number of threads:
		ExecutorService exec = Executors.newFixedThreadPool(5);
		for (int i = 0; i < 5; i++)
		{
			exec.execute(new LiftOff());
		}
		exec.shutdown();
	}
}
