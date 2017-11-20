package com.baohongfei.tij.dp.singleton;

/**
 * 
 * @author baohongfei
 *
 */
public final class HungrySingleton
{
	private static HungrySingleton instance = new HungrySingleton();

	private HungrySingleton()
	{
	}

	public static HungrySingleton getInstance()
	{
		return instance;
	}

}
