package com.baohongfei.dp.singleton;

public final class LasySingleton
{
	private static LasySingleton instance = null;

	private LasySingleton()
	{
	}

	public static LasySingleton getInstance()
	{
		if (instance == null)
		{
			try
			{
				Thread.sleep(1000);
			} catch (InterruptedException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			instance = new LasySingleton();
		}
		return instance;
	}
}
