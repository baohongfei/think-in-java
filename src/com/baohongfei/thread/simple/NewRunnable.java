package com.baohongfei.thread.simple;

/**
 * NewRunnable
 *
 * @author BaoHongfei
 * @since 2014年12月23日 下午8:56:50
 */
public class NewRunnable
{
	public static void main(String[] args)
	{
		Runnable runnable = new Runnable()
		{
			@Override
			public void run()
			{
				System.out.println("Run");
			}
		};

		new Thread(runnable).start();
	}
}
