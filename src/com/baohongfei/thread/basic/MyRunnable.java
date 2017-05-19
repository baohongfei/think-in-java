package com.baohongfei.thread.basic;

/**
 * MyRunnable
 *
 * @author BaoHongfei
 * @since 2014年12月22日 上午11:20:16
 */
public class MyRunnable implements Runnable
{
	private String name;
	
	public MyRunnable(String name)
	{
		super();
		this.name = name;
	}

	@Override
	public void run()
	{
		for (int i = 0; i < 10; i++)
		{
			try
			{
				Thread.sleep(1000);
			} catch (InterruptedException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(name + "|i=" + i);
		}
	}

}
