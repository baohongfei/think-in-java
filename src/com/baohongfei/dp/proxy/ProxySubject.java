package com.baohongfei.dp.proxy;

public class ProxySubject extends Subject
{

	private RealSubject realSubject;

	@Override
	public void request()
	{
		this.preRequest();
		
		if (null == realSubject)
		{
			realSubject = new RealSubject();
		}
		
		realSubject.request();
		
		this.postRequest();
	}

	private void preRequest()
	{
		System.out.println("pre request From proxy subject");
	}

	private void postRequest()
	{
		System.out.println("post request From proxy subject");
	}

}
