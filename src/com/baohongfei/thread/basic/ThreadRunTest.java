package com.baohongfei.thread.basic;

/**
 * ThreadRunTest
 *
 * @author BaoHongfei
 * @since 2014年12月22日 上午10:57:04
 */
public class ThreadRunTest
{
	public static void main(String[] args)
	{
		MyThread mt1 = new MyThread("ThreadA","run");
		MyThread mt2 = new MyThread("ThreadB","run");
		mt1.run();
		mt2.run();
	}
}
