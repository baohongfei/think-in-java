package com.baohongfei.dp.singleton;

/**
 * @author baohongfei
 *
 */
public class HungrySingletonTest
{
	public static void main(String[] args)
	{
		Object o1 = new Object();
		Object o2 = new Object();
		System.out.println(o1 == o2);

		HungrySingleton s1 = HungrySingleton.getInstance();
		HungrySingleton s2 = HungrySingleton.getInstance();
		System.out.println(s1 == s2);
	}

}
