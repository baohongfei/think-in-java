package com.baohongfei.tij.tij4.concurrency.p08;

public class SerialNumberGenerator
{
	private static volatile int serialNumber = 0;

	public static int nextSerialNumber()
	{
		return serialNumber++; // Not thread-safe
	}
}
