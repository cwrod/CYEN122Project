package toolbox;

import com.badlogic.gdx.Gdx;

import gui.GUIHandler;

public class DeltaTime
{
	private static boolean paused = false;

	public static float get()
	{
		if(paused)
			return 0;
		return Gdx.graphics.getDeltaTime();
	}
	
	public static boolean isPaused()
	{
		return paused;
	}
	public static void togglePause()
	{
		paused = !paused;
		GUIHandler.getGUIHandler().setPause(paused);
		
	}
}
