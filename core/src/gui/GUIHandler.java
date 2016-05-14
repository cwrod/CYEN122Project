package gui;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Color;

import game.MainGame;
import game.MainGame.Level;
import graphics.Canvas;
import graphics.Canvas.LayerType;
import graphics.GraphicComponent;
import graphics.TempComponent;
import graphics.TextComponent;
import items.OnHand;
import items.Relic;
import prayer.GloryBe;
import prayer.OurFather;
import prayer.Prayer;

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
		guiHandlerSingleton = new GUIHandler();
		GUIHandler.getGUIHandler().init(l);
	}

	public GUIHandler()
	{
		
	}

	private HealthBar health;
	private Inventory inventory;
	private PrayerBar prayerBar;
	private ArrayList<Button> interactableObjects;
	
	private TempComponent notification; //TODO add notification to screen. One component that changes from public function. 

	public void init(Level l)
	{
		interactableObjects = new ArrayList<Button>();
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
				interactableObjects.add(new LevelLoadButton(30, 190, 201, 44, "newGameButton",null));
				Button continueButton = new Button(30, 160, 165, 42, "continueButton");
				continueButton.addButtonListener(new ButtonListener()
						{

							@Override
							public void onButtonPressed(Button b)
							{
								MainGame.getMainGame().getGameData().load();
								
							}
					
						}	
						);
				interactableObjects.add(continueButton);
				
				return; 
			case FIRST_PRAYER:
				makePrayerChoices("gloryBe",130,45,"ourFather",151,31,0);
				return;
			case SECOND_PRAYER:
				makePrayerChoices("deusVult",125,26,"tueriCorpus",160,34,1);
				return; 
			case THIRD_PRAYER:
				makePrayerChoices("slowTheUnholy",122,66,"wingsOfAngels",202,26,2);
				return;
			case FOURTH_PRAYER:
				makePrayerChoices("boltOfGod",141,29,"holyRetribution",202,33,3);
				return;
			case FAMINE_WON:
				new GraphicComponent(0, 0, Canvas.WIDTH, Canvas.HEIGHT, "gameWonFamine", LayerType.GUI);
				interactableObjects.add(new LevelLoadButton(300,100));
				return; 
			case PLAGUE_WON:
				new GraphicComponent(0, 0, Canvas.WIDTH, Canvas.HEIGHT, "gameWonPlague", LayerType.GUI);
				interactableObjects.add(new LevelLoadButton(300,100));
				return; 
			case WAR_WON:
				new GraphicComponent(0, 0, Canvas.WIDTH, Canvas.HEIGHT, "gameWonWar", LayerType.GUI);
				interactableObjects.add(new LevelLoadButton(300,100));
				return;
			case DEATH_WON:
				new GraphicComponent(0, 0, Canvas.WIDTH, Canvas.HEIGHT, "gameWonDeath", LayerType.GUI);
				interactableObjects.add(new LevelLoadButton(300,100,Level.MAIN_MENU));
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
	
	private void makePrayerChoices(String prayer1,  int width1, int height1,String prayer2, int width2, int height2, int level)
	{
		new GraphicComponent(0, 0, Canvas.WIDTH, Canvas.HEIGHT, "prayerChoiceScreen", LayerType.GUI);
		PrayerChooser pc = new PrayerChooser(level);
		RadioButton r1 = new RadioButton(350, 250, width1, height1, prayer1+"Choice");
		RadioButton r2 = new RadioButton(0, 250, width2, height2, prayer2+"Choice");
		interactableObjects.add(r1);
		interactableObjects.add(r2);
		pc.put(r1,Prayer.getPrayerInstance(prayer1));
		pc.put(r2,Prayer.getPrayerInstance(prayer2));
		r1.addButtonListener(pc);
		r2.addButtonListener(pc);
		interactableObjects.add(new LevelLoadButton(180,0));
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
	
	public void changeNotification(String newText,Color c)
	{
		if(notification != null)
			notification.kill();
		notification = new TempComponent(2.0f,new TextComponent(0, Canvas.HEIGHT-75, 500, 50, newText, LayerType.GUI,c));
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
	public void minimizeInventory()
	{
		inventory.kill();
		Button maximizeButton = new Button(470,470,20,20,"maximize");
		maximizeButton.addButtonListener(new ButtonListener()
				{

					@Override
					public void onButtonPressed(Button b)
					{
						GUIHandler.getGUIHandler().maximizeInventory();
						GUIHandler.getGUIHandler().removeInteractableObject(b);
						b.kill();
					}
				});
		addInteractableObject(maximizeButton);
	}

	public void maximizeInventory()
	{
		inventory = new Inventory(Canvas.WIDTH - 150, 100, 140, 300);
	}
}