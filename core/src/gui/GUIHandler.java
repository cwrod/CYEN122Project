package gui;

import java.util.ArrayList;

import game.MainGame;
import game.MainGame.Level;
import graphics.Canvas;
import graphics.Canvas.LayerType;
import graphics.GraphicComponent;
import items.OnHand;
import items.Relic;
import prayer.OurFather;

public class GUIHandler
{

	// Basic Singleton header

	private static GUIHandler guiHandlerSingleton;

	public static GUIHandler getGUIHandler()
	{
		if (guiHandlerSingleton == null)
		{
			reset(Level.FAMINE);
		}
		return guiHandlerSingleton;
	}
	
	public static void reset(Level l)
	{
		guiHandlerSingleton = new GUIHandler(l);	
	}

	public GUIHandler(Level l)
	{
		init(l);
	}

	private HealthBar health;
	private Inventory inventory;
	private PrayerBar prayerBar;
	private ArrayList<Button> interactableObjects;
	private Level currentLevel;

	private void init(Level l)
	{
		interactableObjects = new ArrayList<Button>();
		currentLevel = l;
		if(l.isPlayable())
		{
		health = new HealthBar(30, 30, 200, 20);
		inventory = new Inventory(Canvas.WIDTH - 150, 100, 140, 300);
		prayerBar = new PrayerBar(0, Canvas.HEIGHT-50, 50);
		}
		else
		{
			switch(l)
			{
			case MAIN_MENU:
				new GraphicComponent(0, 0, Canvas.WIDTH, Canvas.HEIGHT, "inventory", LayerType.GUI);
				interactableObjects.add(new LevelLoadButton(10,200));
				return; 
			case FIRST_PRAYER:
				new GraphicComponent(0, 0, Canvas.WIDTH, Canvas.HEIGHT, "inventory", LayerType.GUI);
				PrayerChooser pc = new PrayerChooser(0);
				RadioButton r = new RadioButton(250, 250, 100, 100, "ourFatherChoice");
				interactableObjects.add(r);
				pc.put(r,new OurFather());
				interactableObjects.add(new LevelLoadButton(100,0));
				return; 
			case FAMINE_WON:
				new GraphicComponent(0, 0, Canvas.WIDTH, Canvas.HEIGHT, "gameWon", LayerType.GUI);
				interactableObjects.add(new LevelLoadButton(10,200));
				return; 
			case GAME_WON:
				new GraphicComponent(0, 0, Canvas.WIDTH, Canvas.HEIGHT, "gameWon", LayerType.GUI);
				interactableObjects.add(new ExitButton(200,200));
				return;
			case GAME_LOST:
				new GraphicComponent(0, 0, Canvas.WIDTH, Canvas.HEIGHT, "gameOver", LayerType.GUI);
				interactableObjects.add(new ExitButton(200,200,.5f));
				interactableObjects.add(new LevelLoadButton(10,200,.5f,MainGame.getMainGame().getGameData().getLastCheckPoint()));
				return;
			}
		}
	}

	public void updateHealth(float healthPercent)
	{
		health.updateHealth(healthPercent);
	}

	public void updateOnHand(OnHand newOnHandWeapon)
	{
		inventory.updateOnHand(newOnHandWeapon);
	}

	public void updateRelic(Relic newRelic)
	{
		inventory.updateRelic(newRelic);
	}

	public void update()
	{
		if(prayerBar!=null)
			prayerBar.update();
	} 
	public PrayerBar getPrayerBar()
	{
		return prayerBar;
	}
	public boolean wasGUIPressed(int x, int y)
	{
		for(Button b : interactableObjects)
		{
			if(x>b.getX()&&x<b.getX()+b.getXSize())
			{
				if(y>b.getY()&&y<b.getY()+b.getYSize())
				{
					b.buttonPressed();
					return true;
				}
			}
		}
		return false;
	}


}