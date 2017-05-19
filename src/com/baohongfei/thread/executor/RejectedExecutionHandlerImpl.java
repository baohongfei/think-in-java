package com.baohongfei.thread.executor;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * RejectedExecutionHandlerImpl
 *
 * @author BaoHongfei
 * @since 2014年12月23日 下午7:36:38
 */
public class RejectedExecutionHandlerImpl implements RejectedExecutionHandler
{

	@Override
	public void rejectedExecution(Runnable r, ThreadPoolExecutor executor)
	{
		System.out.println(r.toString() + " is rejected");
	}

}
