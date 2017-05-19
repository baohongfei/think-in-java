package com.baohongfei.dp.singleton;

public class LasySingletonTest
{
	static LasySingleton s1 = null;
	static LasySingleton s2 = null;

	public static void main(String[] args)
	{
		new Thread1().start();
		new Thread2().start();

		try
		{
			Thread.sleep(2000);
		} catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println(s1);
		System.out.println(s2);
		System.out.println(s1 == s2);

	}

	static class Thread1 extends Thread
	{
		@Override
		public void run()
		{
			s1 = LasySingleton.getInstance();
		}
	}

	static class Thread2 extends Thread
	{
		@Override
		public void run()
		{
			s2 = LasySingleton.getInstance();
		}
	}
}
