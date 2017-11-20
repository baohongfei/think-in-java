package com.baohongfei.tij.thread.executor;

/**
 * WorkerThread
 *
 * @author BaoHongfei
 * @since 2014年12月23日 下午5:19:31
 * @see http://www.importnew.com/8542.html
 */
public class WorkerThread implements Runnable
{

	private String command;

	public WorkerThread(String s)
	{
		this.command = s;
	}

	@Override
	public void run()
	{
		System.out.println(Thread.currentThread().getName()
				+ " Start. Command = " + command);
		processCommand();
		System.out.println(Thread.currentThread().getName() + " End.");
	}

	private void processCommand()
	{
		try
		{
			Thread.sleep(5000);
		} catch (InterruptedException e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public String toString()
	{
		return this.command;
	}
}
