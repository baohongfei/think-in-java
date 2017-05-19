package com.baohongfei.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflectTester
{

	// 对象拷贝
	public Object copy(Object object) throws Exception
	{
		// 获取某个类或某个对象所对应的Class对象的常用的3种方式
		// a 使用Class 类的静态方法 forName:Class.forName("java.lang.String")
		// b 使用类的.class语法：String.class
		// c 使用对象的getClass()方法：String s = "aa";Class<?> clazz = s.getClass();
		Class<?> classType = object.getClass();
		System.out.println(classType);
		System.out.println(classType.getName());

		Constructor cons = classType.getConstructor(new Class[] {});
		Object obj = cons.newInstance(new Object[] {});

		// 以上两行代码等价于下面一行
		Object obj2 = classType.newInstance();

		Constructor cons2 = classType.getConstructor(new Class[] {
				String.class, int.class });
		Object obj3 = cons2.newInstance(new Object[] { "hello", 3 });

		System.out.println(obj);
		System.out.println(obj2);
		System.out.println(obj3);

		Object objectCopy = classType.getConstructor(new Class[] {})
				.newInstance(new Object[] {});

		// 获得对象的所有成员变量
		Field[] fields = classType.getDeclaredFields();
		for (Field field : fields)
		{
			System.out.println(field);
			String name = field.getName();
			// 将属性的首字母转换为大写
			String firstLetter = name.substring(0, 1).toUpperCase();
			String getMethodName = "get" + firstLetter + name.substring(1);
			String setMethodName = "set" + firstLetter + name.substring(1);

			Method getMethod = classType.getMethod(getMethodName,
					new Class[] {});
			Method setMethod = classType.getMethod(setMethodName,
					new Class[] { field.getType() });

			Object value = getMethod.invoke(object, new Object[] {});
			setMethod.invoke(objectCopy, new Object[] { value });
		}

		return objectCopy;
	}

	public static void main(String[] args) throws Exception
	{
		Customer customer = new Customer("Terry", 26);
		customer.setId(198717L);
		ReflectTester test = new ReflectTester();
		Customer customer2 = (Customer) test.copy(customer);
		System.out.println(customer2.getId() + "," + customer2.getName() + ","
				+ customer2.getAge());
	}

}

class Customer
{
	private Long id;

	private String name;

	private int age;

	public Customer()
	{

	}

	public Customer(String name, int age)
	{
		this.name = name;
		this.age = age;
	}

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public int getAge()
	{
		return age;
	}

	public void setAge(int age)
	{
		this.age = age;
	}

}
