package com.baohongfei.tij.thread.basic;

/**
 * ThreadStartTest
 *
 * @author BaoHongfei
 * @since 2014年12月23日 上午11:01:04
 */
public class ThreadStartTest
{
	public static void main(String[] args)
	{
		MyThread mt1 = new MyThread("ThreadA", "start");
		MyThread mt2 = new MyThread("ThreadB", "start");
		mt1.start();
		mt2.start();
	}

}
