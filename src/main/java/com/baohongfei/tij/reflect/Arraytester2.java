package com.baohongfei.tij.reflect;

import java.lang.reflect.Array;

public class Arraytester2
{
	public static void main(String[] args) throws Exception
	{
		int[] dims = new int[] { 5, 10, 15 };

		System.out.println(Integer.TYPE);//Console: int
		System.out.println(Integer.class);//Console: class java.lang.Integer
		
		//array是一个三维数组
		Object array = Array.newInstance(Integer.TYPE, dims);
		
		//arrayObj是一个二维数组
		Object arrayObj = Array.get(array, 3);
		Class<?> classType = arrayObj.getClass().getComponentType();
		
		//arrayObj是一个一维数组
		arrayObj = Array.get(arrayObj, 5);
		Array.setInt(arrayObj, 10, 37);
		
		int[][][] arrayCast = (int[][][])array;
		System.out.println(arrayCast[3][5][10]);
		
		

	}
}
