package toolbox;

import com.badlogic.gdx.Gdx;

import gui.GUIHandler;

public class DeltaTime
{
	private boolean paused = false;
	private float timeFactor = 1;
	
	private static DeltaTime deltaTimeSingleton;

	public static DeltaTime getDeltaTime()
	{
		if (deltaTimeSingleton == null)
		{
			deltaTimeSingleton = new DeltaTime();
		}

		return deltaTimeSingleton;
	}
	
	public static void reset()
	{
		deltaTimeSingleton = new DeltaTime();
	}

	

	public float get()
	{
		if(paused)
			return 0;
		return Gdx.graphics.getDeltaTime();
	}
	public float getForEnemy()
	{
		return get()*timeFactor;
	}
	
	public boolean isPaused()
	{
		return paused;
	}
	public void togglePause()
	{
		paused = !paused;
		GUIHandler.getGUIHandler().setPause(paused);
		
	}

	public void modTime(float timeFactor)
	{
		this.timeFactor*=timeFactor;
		
	}
}
