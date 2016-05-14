package game;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

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
import userInterface.Debug;
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
		
		
		public void save()
		{
			try
			{
				FileWriter fw = new FileWriter("res/saves/game.txt");
				fw.write(lastCheckpoint.getLevelNumber()+"\n");
				fw.write(lastWeapon.getID()+"\n");
				fw.write(lastRelic.getID()+"\n");
				for(int i = 0; i < prayers.length; i++)
				{
					if(prayers[i]!=null)
						fw.write(prayers[i].getID()+"\n");
				}
				fw.close();
			}
			catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		public void load()
		{
			try
			{
				BufferedReader br = new BufferedReader(new FileReader("res/saves/game.txt"));
				lastCheckpoint = Level.getLevel(Integer.parseInt(br.readLine()));
				lastWeapon = (OnHand) Map.getInstanceOfSpawnType(br.readLine());
				lastRelic = (Relic) Map.getInstanceOfSpawnType(br.readLine());
				String nextLine = br.readLine();
				int counter = 0;
				while(nextLine != null)
				{
					prayers[counter] = Prayer.getPrayerInstance(nextLine);
					counter++;
					nextLine = br.readLine();
				}
				br.close();
				MainGame.getMainGame().changeLevel(lastCheckpoint);
			}
			catch (FileNotFoundException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			catch (NumberFormatException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
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
		FAMINE_WON,
		SECOND_PRAYER,
		PLAGUE(2),
		PLAGUE_WON,
		THIRD_PRAYER,
		WAR(3),
		WAR_WON,
		FOURTH_PRAYER,
		DEATH(4),
		DEATH_WON,
		GAME_WON,
		GAME_LOST;
		private boolean playable;
		private int levelNumber;
		private static ArrayList<Level> playableLevels;
		Level(int levelNumber)
		{
			this.playable = true;
			this.levelNumber = levelNumber;
		}
		public static Level getLevel(int levelToGet)
		{
			if(playableLevels == null)
				initLevels();
			for(Level l : playableLevels)
			{
				if(l.getLevelNumber() == levelToGet)
					return l;
			}
			System.out.println(levelToGet+": index not found");
			return null;
			
		}
		private static void initLevels()
		{
			playableLevels = new ArrayList<Level>();
			playableLevels.add(FAMINE);
			playableLevels.add(PLAGUE);
			playableLevels.add(WAR);
			playableLevels.add(DEATH);
			
			
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
				return SECOND_PRAYER;
			case SECOND_PRAYER:
				return PLAGUE;
			case PLAGUE:
				return PLAGUE_WON;
			case PLAGUE_WON:
				return THIRD_PRAYER;
			case THIRD_PRAYER:
				return WAR;
			case WAR:
				return WAR_WON;
			case WAR_WON:
				return FOURTH_PRAYER;
			case FOURTH_PRAYER:
				return DEATH;
			case DEATH:
				return DEATH_WON;
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
		nextLevel = newLevel;
		if(newLevel.isPlayable())
			loadLevel = 0;
		else
			loadLevel = 2;
	}
	private void changeLevel()
	{
		if(nextLevel!= null)
			currentLevel = nextLevel;
		if(currentLevel.isPlayable())
		{
			gd.setLastCheckpoint(currentLevel);
			gd.save();
		}
			
		reset();
		loadLevel = 3;
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
		

	}
	
	private void init()
	{
		ih = new InputHandler();
		gd = new GameData();
		ImageLibrary.reset();
		Map.initSpawnTypes();
		PlayerObject.getPlayerObject().saveCharData();
		reset();
	}
	
	private void reset()
	{
		if(currentLevel.isPlayable())
		{
			DeltaTime.reset();
			Canvas.reset();
			ColliderHandler.reset();
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
			AudioHandler.getAudioLibrary().softReset(currentLevel);
		}

		GUIHandler.reset(currentLevel);
	}

	private Level nextLevel;
	private int loadLevel = -2;
	/*
	 * Another method from libgdx. Gets called every frame. All updates should
	 * be called from here.
	 */
	@Override
	public void render()
	{
		if(loadLevel == -1)
		{
			init();
			loadLevel = 3;
			return;
		}
		if(loadLevel==0||loadLevel==1||loadLevel==-2)
		{

			Gdx.gl.glClearColor(0, 0, 0, 1);
			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
			batch.begin();
			batch.draw(new TextureRegion(new Texture("res/cutscenes/loading.png"), 0, 0, 1.0f, 1.0f), 0, 0, Canvas.WIDTH,Canvas.HEIGHT);
			batch.end();
			loadLevel++;
			return;
		}
		if(loadLevel==2)
		{
			changeLevel();
			return;
		}
		
		ih.update(currentLevel.isPlayable());
		if(currentLevel.isPlayable()&&!DeltaTime.getDeltaTime().isPaused())
		{
			EnemyHandler.getEnemyHandler().update();
			BuildingHandler.getBuildingHandler().update();
			PlayerObject.getPlayerObject().update();
			GameObjectHandler.getEnemyHandler().update();
			QuestHandler.getQuestHandler().update();
		}	
		GUIHandler.getGUIHandler().update();
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		Canvas.getCanvas().paint(batch);
		batch.end();
	}




}
