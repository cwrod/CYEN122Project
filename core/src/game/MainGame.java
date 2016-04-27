package game;

import java.util.ArrayList;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import ai.EnemyHandler;
import ai.GameObjectHandler;
import audio.AudioHandler;
import gameObjects.PlayerObject;
import graphics.Canvas;
import graphics.ImageLibrary;
import gui.GUIHandler;
import items.OnHand;
import items.Relic;
import map.BuildingHandler;
import map.Map;
import physics.ColliderHandler;
import prayer.Prayer;
import quest.QuestHandler;
import toolbox.DeltaTime;
import userInterface.InputHandler;

/*
 * The main entry point for the game. This handles the game after the Desktop Launcher
 * sets up the actual game screen. All loading of game assets should go here. Progressing 
 * through the game and loading new levels will probably also end up here.
 */
public class MainGame extends ApplicationAdapter
{
	public class GameData
	{
		private Level lastCheckpoint;
		private OnHand lastWeapon;
		private Relic lastRelic;
		private Prayer[] prayers = new Prayer[4];
		public void setLastCheckpoint(Level lastCheckpoint)
		{
			this.lastCheckpoint = lastCheckpoint;
		}
		public Level getLastCheckPoint()
		{
			return lastCheckpoint;
		}
		public OnHand getLastWeapon()
		{
			return lastWeapon;
		}
		public void setLastWeapon(OnHand lastWeapon)
		{
			this.lastWeapon = lastWeapon;
		}
		public Relic getLastRelic()
		{
			return lastRelic;
		}
		public void setLastRelic(Relic lastRelic)
		{
			this.lastRelic = lastRelic;
		}
		public Prayer[] getPrayers()
		{
			return prayers;
		}
		public void setPrayer(int index, Prayer prayer)
		{
			prayers[index] = prayer;
		}
		
	}
	
	private SpriteBatch batch;
	private InputHandler ih;
	private Level currentLevel;
	private GameData gd;
	
	private static MainGame m;
	
	public static MainGame getMainGame()
	{
		return m;
	}
	
	public enum Level
	{
		MAIN_MENU,
		FIRST_PRAYER,
		FAMINE(1),
		SECOND_PRAYER,
		FAMINE_WON,
		PLAGUE(2),
		PLAGUE_WON,
		WAR(3),
		WAR_WON,
		DEATH(4),
		GAME_WON,
		GAME_LOST;
		private boolean playable;
		private int levelNumber;
		Level(int levelNumber)
		{
			this.playable = true;
			this.levelNumber = levelNumber;
		}
		Level()
		{
			playable = false;
			levelNumber = -1;
		}
		public boolean isPlayable()
		{
			return playable;
		}
		public int getLevelNumber()
		{
			return levelNumber;
		}
		public Level getNextLevel()
		{
			switch(this)
			{
			case MAIN_MENU:
				return FIRST_PRAYER;
			case FIRST_PRAYER:
				return FAMINE;
			case FAMINE:
				return FAMINE_WON;
			case FAMINE_WON:
				return PLAGUE;
			case WAR:
				return DEATH;
			case DEATH:
				return GAME_WON;
			default:
				return null;
				
			}
		}
	}
	
	public void incrementLevel()
	{
		changeLevel(getNextLevel());
		PlayerObject.getPlayerObject().saveCharData();
		
	}
	public Level getNextLevel()
	{
		return currentLevel.getNextLevel();
	}
	public Level getCurrentLevel()
	{
		return currentLevel;
	}
	
	public GameData getGameData()
	{
		return gd;
	}
	
	
	public void changeLevel(Level newLevel)
	{
		currentLevel = newLevel;
		if(newLevel.isPlayable())
		{
			gd.setLastCheckpoint(newLevel);
			
		}
			
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
		currentLevel = Level.MAIN_MENU;
		ih = new InputHandler();
		gd = new GameData();
		Map.initSpawnTypes();
		PlayerObject.getPlayerObject();
		PlayerObject.getPlayerObject().saveCharData();
		reset();
	}
	
	private void reset()
	{
		if(currentLevel.isPlayable())
		{
			ImageLibrary.reset();
			Canvas.reset();
			ColliderHandler.reset();
			GUIHandler.reset(currentLevel);
			PlayerObject.getPlayerObject().softReset();
			AudioHandler.getAudioLibrary().softReset(currentLevel);
			QuestHandler.reset();
			EnemyHandler.reset();
			BuildingHandler.reset();
			Map.generate(80, "level"+currentLevel.getLevelNumber());
		}
		else
		{
			Canvas.reset();
		}

		GUIHandler.reset(currentLevel);
	}


	/*
	 * Another method from libgdx. Gets called every frame. All updates should
	 * be called from here.
	 */
	@Override
	public void render()
	{
		ih.update(currentLevel.isPlayable());
		if(currentLevel.isPlayable()&&!DeltaTime.isPaused())
		{
			EnemyHandler.getEnemyHandler().update();
			BuildingHandler.getBuildingHandler().update();
			PlayerObject.getPlayerObject().update();
			GameObjectHandler.getEnemyHandler().update();
		}	
			GUIHandler.getGUIHandler().update();
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		Canvas.getCanvas().paint(batch);
		batch.end();
	}




}
