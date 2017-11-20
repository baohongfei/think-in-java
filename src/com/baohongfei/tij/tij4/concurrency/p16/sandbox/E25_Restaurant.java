package com.baohongfei.tij.tij4.concurrency.p16.sandbox;

/********************** Exercise 25 ***********************
 * In the Chef class in Restaurant.java, return from run()
 * after calling shutdownNow() and observe the difference
 * in behavior.
 *********************************************************/
import static com.baohongfei.tij.tij4.net.mindview.util.Print.print;
import static com.baohongfei.tij.tij4.net.mindview.util.Print.printnb;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Meal
{
    private final int orderNum;

    public Meal(int orderNum)
    {
        this.orderNum = orderNum;
    }

    @Override
    public String toString()
    {
        return "Meal " + orderNum;
    }
}

class WaitPerson implements Runnable
{
    private Restaurant restaurant;

    public WaitPerson(Restaurant r)
    {
        restaurant = r;
    }

    @Override
    public void run()
    {
        try
        {
            while (!Thread.interrupted())
            {
                synchronized (this)
                {
                    while (restaurant.meal == null)
                        wait(); // ... for the chef to produce a meal
                }
                print("Waitperson got " + restaurant.meal);
                synchronized (restaurant.chef)
                {
                    restaurant.meal = null;
                    restaurant.chef.notifyAll(); // Ready for another
                }
            }
        } catch (InterruptedException e)
        {
            print("WaitPerson interrupted");
        }
    }
}

class Chef implements Runnable
{
    private Restaurant restaurant;
    private int count = 0;

    public Chef(Restaurant r)
    {
        restaurant = r;
    }

    @Override
    public void run()
    {
        try
        {
            while (!Thread.interrupted())
            {
                synchronized (this)
                {
                    while (restaurant.meal != null)
                        wait(); // ... for the meal to be taken
                }
                if (++count == 10)
                {
                    print("Out of food, closing");
                    restaurant.exec.shutdownNow();
                    return;
                }
                printnb("Order up! ");
                synchronized (restaurant.waitPerson)
                {
                    restaurant.meal = new Meal(count);
                    restaurant.waitPerson.notifyAll();
                }
                TimeUnit.MILLISECONDS.sleep(100);
            }
        } catch (InterruptedException e)
        {
            print("Chef interrupted");
        }
    }
}

class Restaurant
{
    Meal meal;
    ExecutorService exec = Executors.newCachedThreadPool();
    WaitPerson waitPerson = new WaitPerson(this);
    Chef chef = new Chef(this);

    public Restaurant()
    {
        exec.execute(chef);
        exec.execute(waitPerson);
    }

    public static void main(String[] args)
    {
        new Restaurant();
    }
}

public class E25_Restaurant
{
    public static void main(String[] args)
    {
        new Restaurant();
    }
}
