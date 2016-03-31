package toolbox;

import java.util.ArrayList;
import java.util.HashMap;

public class Time {
	private double deltaTime;
	private long lastTime;
	
	
	private static Time timeSingleton;
	
	private HashMap<String, Timer> timers;
	
	public static Time getTime()
	{
		if(timeSingleton==null)
		{
			timeSingleton = new Time();
		}
		return timeSingleton;
	}
	
	public Time(){
		lastTime = System.currentTimeMillis();
		deltaTime = 0;
		timers = new  HashMap<String, Timer>();
	}
	
	
	
	public void tick()
	{
		deltaTime=(double)((System.currentTimeMillis()-lastTime)/1000.0);
		lastTime=System.currentTimeMillis();
	}
	
	public double getDeltaTime()
	{
		return deltaTime;
		
	}
	
	public boolean addTimer(String key, double delay)
	{
		if(timers.containsKey(key))
		{
			if(timers.get(key).isDone())
			{
			timers.remove(key);
			}
			else
			{
				return true;	
			}	
		}
			
		timers.put(key, new Timer(delay));
		return false;
	}
}
