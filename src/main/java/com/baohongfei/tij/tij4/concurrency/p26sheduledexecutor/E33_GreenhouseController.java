package com.baohongfei.tij.tij4.concurrency.p26sheduledexecutor;

//{Args: 5000}
/******************** Exercise 33 ************************
 * Modify GreenhouseScheduler.java to use a
 * DelayQueue instead of a ScheduledExecutor.
 *********************************************************/
import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.TimeUnit.NANOSECONDS;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

abstract class Event implements Runnable, Delayed
{
    protected final long delayTime;
    private long trigger;

    public Event(long delayTime)
    {
        this.delayTime = delayTime;
    }

    @Override
    public long getDelay(TimeUnit unit)
    {
        return unit.convert(trigger - System.nanoTime(), NANOSECONDS);
    }

    @Override
    public int compareTo(Delayed arg)
    {
        Event that = (Event) arg;
        if (trigger < that.trigger)
            return -1;
        if (trigger > that.trigger)
            return 1;
        return 0;
    }

    public void start()
    { // Allows restarting
        trigger = System.nanoTime()
                + NANOSECONDS.convert(delayTime, MILLISECONDS);
    }

    @Override
    public abstract void run();
}

class Controller implements Runnable
{
    private DelayQueue<Event> q;

    public Controller(DelayQueue<Event> q)
    {
        this.q = q;
    }

    @Override
    public void run()
    {
        try
        {
            while (!Thread.interrupted())
            {
                Event event = q.take();
                System.out.println(event);
                event.run();
            }
        } catch (InterruptedException e)
        {
            // Acceptable way to exit
        }
        System.out.println("Finished Controller");
    }

    public void addEvent(Event c)
    {
        c.start();
        q.put(c);
    }
}

class GreenhouseControls extends Controller
{
    public GreenhouseControls(DelayQueue<Event> q)
    {
        super(q);
    }

    private boolean light;

    public class LightOn extends Event
    {
        public LightOn(long delayTime)
        {
            super(delayTime);
        }

        @Override
        public void run()
        {
            // Put hardware control code here to
            // physically turn on the light.
            light = true;
        }

        @Override
        public String toString()
        {
            return "Light is on";
        }
    }

    public class LightOff extends Event
    {
        public LightOff(long delayTime)
        {
            super(delayTime);
        }

        @Override
        public void run()
        {
            // Put hardware control code here to
            // physically turn off the light.
            light = false;
        }

        @Override
        public String toString()
        {
            return "Light is off";
        }
    }

    private boolean water;

    public class WaterOn extends Event
    {
        public WaterOn(long delayTime)
        {
            super(delayTime);
        }

        @Override
        public void run()
        {
            // Put hardware control code here.
            water = true;
        }

        @Override
        public String toString()
        {
            return "Greenhouse water is on";
        }
    }

    public class WaterOff extends Event
    {
        public WaterOff(long delayTime)
        {
            super(delayTime);
        }

        @Override
        public void run()
        {
            // Put hardware control code here.
            water = false;
        }

        @Override
        public String toString()
        {
            return "Greenhouse water is off";
        }
    }

    private String thermostat = "Day";

    public class ThermostatNight extends Event
    {
        public ThermostatNight(long delayTime)
        {
            super(delayTime);
        }

        @Override
        public void run()
        {
            // Put hardware control code here.
            thermostat = "Night";
        }

        @Override
        public String toString()
        {
            return "Thermostat on night setting";
        }
    }

    public class ThermostatDay extends Event
    {
        public ThermostatDay(long delayTime)
        {
            super(delayTime);
        }

        @Override
        public void run()
        {
            // Put hardware control code here.
            thermostat = "Day";
        }

        @Override
        public String toString()
        {
            return "Thermostat on day setting";
        }
    }

    // An example of an action() that inserts a
    // new one of itself into the event list:
    public class Bell extends Event
    {
        public Bell(long delayTime)
        {
            super(delayTime);
        }

        @Override
        public void run()
        {
            addEvent(new Bell(delayTime));
        }

        @Override
        public String toString()
        {
            return "Bing!";
        }
    }

    public class Restart extends Event
    {
        private Event[] eventList;

        public Restart(long delayTime, Event[] eventList)
        {
            super(delayTime);
            this.eventList = eventList;
            for (Event e : eventList)
                addEvent(e);
        }

        @Override
        public void run()
        {
            for (Event e : eventList)
            {
                addEvent(e);
            }
            addEvent(this);
        }

        @Override
        public String toString()
        {
            return "Restarting system";
        }
    }

    public static class Terminate extends Event
    {
        private ExecutorService exec;

        public Terminate(long delayTime, ExecutorService e)
        {
            super(delayTime);
            exec = e;
        }

        @Override
        public void run()
        {
            exec.shutdownNow();
        }

        @Override
        public String toString()
        {
            return "Terminating";
        }
    }
}

/*
 * This program combines concurrency/DelayQueueDemo.java and
 * innerclasses/GreenhouseController.java from TIJ4. Using DelayQueue instead of
 * an ordinary ArrayList cleans up the code a bit.
 */
public class E33_GreenhouseController
{
    public static void main(String[] args)
    {
        ExecutorService exec = Executors.newCachedThreadPool();
        DelayQueue<Event> queue = new DelayQueue<Event>();
        GreenhouseControls gc = new GreenhouseControls(queue);
        gc.addEvent(gc.new Bell(900));
        Event[] eventList = { gc.new ThermostatNight(0), gc.new LightOn(200),
                gc.new LightOff(400), gc.new WaterOn(600),
                gc.new WaterOff(800), gc.new ThermostatDay(1400) };
        gc.addEvent(gc.new Restart(2000, eventList));
        if (args.length == 1)
            gc.addEvent(new GreenhouseControls.Terminate(new Integer(args[0]),
                    exec));
        exec.execute(gc);
    }
}
