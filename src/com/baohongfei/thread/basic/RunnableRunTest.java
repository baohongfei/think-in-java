package com.baohongfei.thread.basic;

/**
 * RunnableRunTest
 *
 * @author BaoHongfei
 * @since 2014年12月22日 上午11:25:45
 */
public class RunnableRunTest
{
	public static void main(String[] args)
	{
		MyRunnable mt1 = new MyRunnable("RunnableA");
		MyRunnable mt2 = new MyRunnable("RunnableB");
		mt1.run();
		mt2.run();
	}
}
