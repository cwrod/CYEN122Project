package game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import ai.EnemyHandler;
import gameObjects.PlayerObject;
import graphics.Canvas;
import graphics.ImageLibrary;
import gui.GUIHandler;
import map.BuildingHandler;
import map.Map;
import physics.ColliderHandler;
import scene.SceneHandler;
import userInterface.InputHandler;

/*
 * The main entry point for the game. This handles the game after the Desktop Launcher
 * sets up the actual game screen. All loading of game assets should go here. Progressing 
 * through the game and loading new levels will probably also end up here.
 */
public class MainGame extends ApplicationAdapter
{
	private SpriteBatch batch;
	private InputHandler ih;
	private Level currentLevel;
	
	private static MainGame m;
	
	public static MainGame getMainGame()
	{
		return m;
	}
	
	public enum Level
	{
		FAMINE,PLAGUE,WAR,DEATH,GAME_WON,GAME_LOST
	}
	
	
	public void changeLevel(Level newLevel)
	{
		currentLevel = newLevel;
		reset();
	}
	
	
	
	/*
	 * A method from ApplicationAdaptor that gets called when the game screen is
	 * made.
	 */
	@Override
	public void create()
	{
		m = this;
		batch = new SpriteBatch();
		currentLevel = Level.FAMINE;
		Map.initSpawnTypes();
		PlayerObject.getPlayerObject();
		reset();
	}
	
	private void reset()
	{
		if(currentLevel.ordinal()<Level.GAME_WON.ordinal())
		{
			ImageLibrary.reset();
			Canvas.reset();
			ColliderHandler.reset();
			ih = new InputHandler();
			PlayerObject.getPlayerObject().softReset();
			GUIHandler.reset();
			EnemyHandler.reset();
			BuildingHandler.reset();
			Map.generate(80, "level"+(currentLevel.ordinal()+1));
		}
		else
		{
			Canvas.reset();
			SceneHandler.reset(currentLevel);
		}
	}


	/*
	 * Another method from libgdx. Gets called every frame. All updates should
	 * be called from here.
	 */
	@Override
	public void render()
	{
		boolean playableLevel = currentLevel.ordinal()<Level.GAME_WON.ordinal();
		ih.update(playableLevel);
		if(playableLevel)
		{
			EnemyHandler.getEnemyHandler().update();
			BuildingHandler.getBuildingHandler().update();
			PlayerObject.getPlayerObject().update();
		}
		else
		{
			SceneHandler.getSceneHandler().update();
		}
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		Canvas.getCanvas().paint(batch);
		batch.end();
	}
}
