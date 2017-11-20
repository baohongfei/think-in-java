package com.baohongfei.tij.reflect;

import java.lang.reflect.Method;

public class DumpMethods
{
	/**
	 * Get Method from class
	 * 
	 * Run Configurations>Arguments>Program arguments>java.lang.Object
	 * 
	 */
	public static void main(String[] args) throws Exception
	{
		Class<?> classType = Class.forName(args[0]);
		
		Method[]  methods = classType.getDeclaredMethods();
		
		for(Method method:methods)
		{
			System.out.println(method);
		}
		
	}

}
