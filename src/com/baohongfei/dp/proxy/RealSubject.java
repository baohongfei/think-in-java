package com.baohongfei.dp.proxy;

public class RealSubject extends Subject
{
	@Override
	public void request()
	{
		System.out.println("From real subject");

	}

}
