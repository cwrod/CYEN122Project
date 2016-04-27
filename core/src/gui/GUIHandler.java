package gui;

import java.util.ArrayList;

import game.MainGame;
import game.MainGame.Level;
import graphics.Canvas;
import graphics.Canvas.LayerType;
import graphics.GraphicComponent;
import graphics.TextComponent;
import items.OnHand;
import items.Relic;
import prayer.GloryBe;
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
	
	private TextComponent notification; //TODO add notification to screen. One component that changes from public function. 

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
				new GraphicComponent(0, 0, Canvas.WIDTH, Canvas.HEIGHT, "titleScreen", LayerType.GUI);
				interactableObjects.add(new LevelLoadButton(30,170));
				return; 
			case FIRST_PRAYER:
				new GraphicComponent(0, 0, Canvas.WIDTH, Canvas.HEIGHT, "inventory", LayerType.GUI);
				PrayerChooser pc = new PrayerChooser(0);
				RadioButton r1 = new RadioButton(250, 250, 100, 100, "ourFatherChoice");
				RadioButton r2 = new RadioButton(0, 250, 100, 100, "gloryBeChoice");
				interactableObjects.add(r1);
				interactableObjects.add(r2);
				pc.put(r1,new OurFather());
				pc.put(r2,new GloryBe());
				r1.addButtonListener(pc);
				r2.addButtonListener(pc);
				interactableObjects.add(new LevelLoadButton(100,0));
				return; 
			case FAMINE_WON:
				new GraphicComponent(0, 0, Canvas.WIDTH, Canvas.HEIGHT, "gameWonFamine", LayerType.GUI);
				interactableObjects.add(new LevelLoadButton(10,200));
				return; 
			case GAME_WON:
				new GraphicComponent(0, 0, Canvas.WIDTH, Canvas.HEIGHT, "gameWon", LayerType.GUI);
				interactableObjects.add(new ExitButton(200,200));
				return;
			case GAME_LOST:
				new GraphicComponent(0, 0, Canvas.WIDTH, Canvas.HEIGHT, "gameOver", LayerType.GUI);
				interactableObjects.add(new ExitButton(200,200));
				interactableObjects.add(new LevelLoadButton(10,200,MainGame.getMainGame().getGameData().getLastCheckPoint()));
				return;
			}
		}
	}
	public void addInteractableObject(Button button)
	{
		interactableObjects.add(button);
	}
	public void removeInteractableObject(Button button)
	{
		interactableObjects.remove(button);
	}

	public void updateHealth(float healthPercent, boolean isPoisoned)
	{
		health.updateHealth(healthPercent,isPoisoned);
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
	
	public void changeNotification(String newText)
	{
		if(notification != null)
			notification.kill();
		notification = new TextComponent(150, Canvas.HEIGHT-75, 500, 50, newText, LayerType.GUI);
	}

	private PauseScreen pauseScreen;
	public void setPause(boolean shouldPause)
	{
		if(pauseScreen!=null)
			pauseScreen.kill();
		if(shouldPause)
		{
			pauseScreen = new PauseScreen();
		}
	}
}