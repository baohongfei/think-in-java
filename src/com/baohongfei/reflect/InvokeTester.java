package com.baohongfei.reflect;

import java.lang.reflect.Method;

public class InvokeTester
{
	public int add(int param1, int param2)
	{
		return param1 + param2;
	}

	public String echo(String message)
	{
		return "hello:" + message;
	}

	public static void main(String[] args) throws Exception
	{
		// Normal invoke
		// InvokeTester test = new InvokeTester();
		// System.out.println(test.add(1, 2));
		// System.out.println(test.echo("World"));

		// Reflect Invoke
		Class<?> classType = InvokeTester.class;
		Object invokeTester = classType.newInstance();
		// Object invokeTester = new InvokeTester();
		
		System.out.println("invokeTester instanceof InvokeTester: "
				+ (invokeTester instanceof InvokeTester));

		Method addMethod = classType.getMethod("add", new Class[] { int.class,
				int.class });
		Object result = addMethod.invoke(invokeTester, new Object[] { 1, 2 });
		// 反射总是返回原声类型的包装类
		System.out.println((Integer) result);

		Method echoMethod = classType.getMethod("echo",
				new Class[] { String.class });
		Object result2 = echoMethod.invoke(invokeTester,
				new Object[] { "World" });
		System.out.println((String) result2);

	}

}
