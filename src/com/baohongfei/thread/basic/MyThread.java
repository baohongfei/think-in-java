package com.baohongfei.thread.basic;

/**
 * MyThread
 * 
 * @see http://developer.51cto.com/art/201203/321042.htm
 * @author BaoHongfei
 * @since 2014年12月22日 上午10:55:13
 */
public class MyThread extends Thread
{
	private String executeMethod;
	
	

	public MyThread(String name, String executeMethod)
	{
		super(name);
		this.executeMethod = executeMethod;
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
			System.out.println(this.getName() + "|" + executeMethod + "|i=" + i);
		}
	}
	
}
