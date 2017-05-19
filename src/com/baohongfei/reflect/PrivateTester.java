package com.baohongfei.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class PrivateTester
{
	public static void main(String[] args) throws Exception
	{
		Private p = new Private();
		Class<?> classType = p.getClass();

		Method method = classType.getDeclaredMethod("sayHello",
				new Class[] { String.class });

		// key line
		method.setAccessible(true);

		String str = (String) method.invoke(p, new Object[] { "Terry" });

		System.out.println(str);

		Private2 p2 = new Private2();
		Class<?> classTypeP2 = p2.getClass();
		Field field = classTypeP2.getDeclaredField("name");
		field.setAccessible(true);
		field.set(p2, "Terry");

		Method methodP2 = classTypeP2.getDeclaredMethod("getName",
				new Class[] {});
		methodP2.setAccessible(true);

		String strP2 = (String) methodP2.invoke(p2, new Object[] {});
		System.out.println(strP2);

	}

}
