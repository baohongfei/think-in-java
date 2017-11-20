package com.baohongfei.tij.tij4.concurrency.p04;

import com.baohongfei.tij.tij4.concurrency.p01.LiftOff;

/******************
 * Exercise 8 ***************** Modify MoreBasicThreads.java so that all the
 * threads are daemon threads, and verify that the program ends as soon as
 * main() is able to exit.
 ***********************************************/
/*
 * Run the program and you see that the daemon threads are unable to complete
 * their countdowns before the program terminates.
 */
public class E08_MoreBasicDaemonThreads
{
	public static void main(String[] args)
	{
		for (int i = 0; i < 5; i++)
		{
			Thread t = new Thread(new LiftOff());
			t.setDaemon(true);
			t.start();
		}
		System.out.println("Waiting for LiftOff");
	}
}
