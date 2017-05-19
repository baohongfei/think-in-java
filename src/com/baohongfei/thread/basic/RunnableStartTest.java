package com.baohongfei.thread.basic;

/**
 * RunnableStartTest
 *
 * @author BaoHongfei
 * @since 2014年12月23日 下午4:09:32
 */
public class RunnableStartTest
{
	public static void main(String[] args)
	{
		MyRunnable mt1 = new MyRunnable("RunnableA");
		Thread t1 = new Thread(mt1,"RunnableA");
		MyRunnable mt2 = new MyRunnable("RunnableB");
		Thread t2 = new Thread(mt2,"RunnableB");
		t1.start();
		t2.start();
	}
}
