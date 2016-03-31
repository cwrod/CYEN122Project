package toolbox;

public class Timer {
	private long finishTime;
	public Timer(double delay)
	{
		finishTime = System.currentTimeMillis() + ((long)(delay*1000));
	}
	public boolean isDone()
	{
		if(finishTime<System.currentTimeMillis())
			return true;
		return false;
	}
}
