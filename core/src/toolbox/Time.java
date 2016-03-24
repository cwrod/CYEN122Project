package toolbox;

public class Time {
	private double deltaTime;
	private long lastTime;
	
	private static Time timeSingleton;
	
	
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
	
}
