package com.baohongfei.tij4.concurrency.p09;

//Atomic classes are occasionally useful in regular code.
//{RunByHand}
import java.util.concurrent.atomic.AtomicInteger;

import com.baohongfei.tij4.concurrency.p07.EvenChecker;
import com.baohongfei.tij4.concurrency.p07.IntGenerator;

public class AtomicEvenGenerator extends IntGenerator
{
	private AtomicInteger currentEvenValue = new AtomicInteger(0);

	public int next()
	{
		return currentEvenValue.addAndGet(2);
	}

	public static void main(String[] args)
	{
		EvenChecker.test(new AtomicEvenGenerator());
	}
}
