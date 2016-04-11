package scene;

import game.MainGame.Level;
import graphics.Canvas;
import graphics.GraphicComponent;
import graphics.Canvas.LayerType;

public class SceneHandler
{
	// Basic header for a singleton

	private static SceneHandler sceneHandlerSingleton;

	public static SceneHandler getSceneHandler()
	{
		if (sceneHandlerSingleton == null)
		{
			reset(Level.FAMINE);
		}

		return sceneHandlerSingleton;
	}
	
	public static void reset(Level l)
	{
		sceneHandlerSingleton = new SceneHandler(l);
	}
	
	public SceneHandler(Level l)
	{
		if(l == Level.GAME_WON)
		{
			new GraphicComponent(0, 0, Canvas.WIDTH, Canvas.HEIGHT, "gameWon", LayerType.GUI);
		}
		if(l == Level.GAME_LOST)
		{
			new GraphicComponent(0, 0, Canvas.WIDTH, Canvas.HEIGHT, "gameOver", LayerType.GUI);
		}
	}

	public void update()
	{
		// TODO Auto-generated method stub
		
	}


}
