package com.baohongfei.tij.dp.singleton;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 
 * http://wuchong.me/blog/2014/08/28/how-to-correctly-write-singleton-pattern/
 *
 * @author BaoHongfei
 * @since 2015年1月12日 上午11:48:59
 * 
 */
public class InnerClassSingleton
{
    private static class SingletonHolder
    {
        private SingletonHolder()
        {
            System.out.println("SingletonHolder struct .");
        }

        // 内部静态类只有在被引用的时候才会被加载
        private static final InnerClassSingleton INSTANCE = new InnerClassSingleton();
    }

    private InnerClassSingleton()
    {
        System.out.println("InnerClassSingleton struct singleton() start");
        try
        {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("singleton() end");

    }

    public static final InnerClassSingleton getInstance()
    {
        System.out.println("getInstance");
        return SingletonHolder.INSTANCE;
    }

    public static void main(String[] args)
    {
        System.out.println("x");

        try
        {
            Class.forName("com.baohongfei.dp.singleton.InnerClassSingleton$1");
            Class.forName("com.baohongfei.dp.singleton.InnerClassSingleton$SingletonHolder");
        } catch (ClassNotFoundException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        Runnable r = new Runnable()
        {
            @Override
            public void run()
            {
                System.out.println(Thread.currentThread());
                System.out.println(InnerClassSingleton.getInstance());
                System.out.println(Thread.currentThread());
            }
        };

        ExecutorService exec = Executors.newCachedThreadPool();

        for (int i = 0; i < 2; i++)
        {
            exec.execute(r);
        }
    }

}
