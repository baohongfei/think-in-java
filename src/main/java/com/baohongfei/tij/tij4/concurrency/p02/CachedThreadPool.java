package com.baohongfei.tij.tij4.concurrency.p02;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.baohongfei.tij.tij4.concurrency.p01.LiftOff;

public class CachedThreadPool
{
	public static void main(String[] args)
	{
		ExecutorService exec = Executors.newCachedThreadPool();
		for (int i = 0; i < 5; i++)
		{
			exec.execute(new LiftOff());
		}
		exec.shutdown();
	}
}
