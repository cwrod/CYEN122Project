package gameObjects;

import java.util.ArrayList;

import audio.AudioHandler;
import game.MainGame;
import game.MainGame.GameData;
import game.MainGame.Level;
import graphics.AnimationComponent;
import graphics.Camera;
import graphics.Canvas;
import graphics.Canvas.LayerType;
import graphics.GraphicComponent;
import gui.GUIHandler;
import items.HolyWater;
import items.Item;
import items.OnHand;
import items.Relic;
import items.RustySword;
import map.BuildingHandler;
import physics.ColliderHandler;
import quest.QuestHandler;
import toolbox.DeltaTime;
import toolbox.Functions;

/*
 * All player data should go here. This also represents the physical location of the character in space.
 * This class may have to be changed depending on what happens with GUI.
 */
public class PlayerObject extends MobileGameObject
{

	public static final int WIDTH = 25;
	public static final int HEIGHT = 25;

	// Basic header for a singleton
	private static PlayerObject playerObjectSingleton;

	public static PlayerObject getPlayerObject()
	{
		if (playerObjectSingleton == null)
		{
			playerObjectSingleton = new PlayerObject();
		}
		return playerObjectSingleton;
	}
	
	
	public void softReset()
	{
		playerObjectSingleton = new PlayerObject(MainGame.getMainGame().getGameData().getLastWeapon(), MainGame.getMainGame().getGameData().getLastRelic());
		onHandWeapon.equip();
		currentRelic.equip();
	}
	
	public void saveCharData()
	{
		GameData gd = MainGame.getMainGame().getGameData();
		gd.setLastRelic(currentRelic);
		gd.setLastWeapon(onHandWeapon);
	}


	private float speed;
	private float health;
	private float maxHealth;

	private OnHand onHandWeapon;
	private Relic currentRelic;

	private boolean canAttack;
	
	private GraphicComponent compass;
	private boolean compassPointsToBoss;
	
	private float modDef;
	private float modAtt;
	
	private ArrayList<Float> poisonMagnitudes;
	private ArrayList<Float> poisonTimes;
	
	private ArrayList<PlayerListener> listeners;
	private ArrayList<PlayerDamageListener> damageListeners;
	
	
	private PlayerActions currentAction;
	
	public PlayerObject(OnHand startWeapon, Relic startRelic)
	{
		super(400, 400, WIDTH, HEIGHT, startWeapon.getType(), LayerType.PLAYER, true, true);
		Camera.getCamera().setPos(x, y);
		speed = 200;
		health = maxHealth = 100;
		canAttack = true;
		compass = new GraphicComponent(Canvas.WIDTH - 50, Canvas.HEIGHT - 50, 25,25, "compass", LayerType.GUI);
		
		modDef = 0.0f;
		modAtt = 1.0f;
		
		onHandWeapon = startWeapon;
		currentRelic = startRelic;
		
		listeners = new ArrayList<PlayerListener>();
		damageListeners = new ArrayList<PlayerDamageListener>();
		
		
		poisonMagnitudes = new ArrayList<Float>();
		poisonTimes = new ArrayList<Float>();
		
		currentAction = PlayerActions.IDLE;
		
	}
	public PlayerObject()
	{
		this(new RustySword(),new HolyWater());
		
	}
	
	
	
	public void addListener(PlayerListener l)
	{
		listeners.add(l);
	}
	public void removeListener(PlayerListener l)
	{
		listeners.remove(l);
	}
	
	public void addDamageListener(PlayerDamageListener l)
	{
		damageListeners.add(l);
	}
	public void removeDamageListener(PlayerDamageListener l)
	{
		damageListeners.remove(l);
	}
	
	public void update()
	{
		currentRelic.update();
		if (((AnimationComponent) gc).isDone("attacking"))
		{
			shouldRotate = true;
			canAttack = true;
			actionPerformed(PlayerActions.IDLE);
		}
		if(compassPointsToBoss)
		{
			compass.setRot((int)Functions.angleMeasure(this,QuestHandler.getQuestHandler().getBoss()));
		}
		else
		{
			compass.setRot((int)Functions.angleMeasure(this, BuildingHandler.getBuildingHandler().closestNonBossBuilding(this)));
		}

		for(int i = 0; i < poisonMagnitudes.size(); i++)
		{
			takeDamage(poisonMagnitudes.get(i)*DeltaTime.getDeltaTime().get(),null);
		}
		ArrayList<Integer> killIndicies = new ArrayList<Integer>();
		for(int i = 0; i < poisonTimes.size(); i++)
		{
			poisonTimes.set(i, poisonTimes.get(i)-DeltaTime.getDeltaTime().get());
			if(poisonTimes.get(i)<=0)
			{
				killIndicies.add(i);
			}
		}
		for(Integer i : killIndicies)
		{
			poisonTimes.remove(i.intValue());
			poisonMagnitudes.remove(i.intValue());
			GUIHandler.getGUIHandler().updateHealth((float) health / (float) maxHealth, poisonMagnitudes.size()>0);

		}
			
	}

	/*
	 * This might not be the most efficient way to do this, but it was the
	 * easiest way I could think of without breaking anything.
	 * 
	 * Basically, the inputHandler calls move left, right, up, or down and then
	 * the game sums up these commands to find out which way the user actually
	 * wants to move.
	 * 
	 * The good thing about this is that I would not have to rewrite a lot to
	 * get this to work with a jostick.
	 * 
	 */

	private float horizontalSums = 0;
	private float verticalSums = 0;

	public void moveLeft()
	{
		horizontalSums -= 1;
	}

	public void moveRight()
	{
		horizontalSums += 1;
	}

	public void moveUp()
	{
		verticalSums += 1;
	}

	public void moveDown()
	{
		verticalSums -= 1;
	}

	private long lastPickup = 0;

	public void pickUp()
	{
		if (System.currentTimeMillis() - lastPickup > 1000)
		{
			ArrayList<GameObject> goList = ColliderHandler.getColliderHandler().getObjectsOverlapping(c);
			if (goList.size() > 0)
			{
				for (GameObject go : goList)
				{
					if (go instanceof PhysicalItem)
					{
						Item foundItem = ((PhysicalItem) go).getAssociatedItem();
						if (foundItem instanceof OnHand)
						{
							((PhysicalItem) go).replace(onHandWeapon);
							equipItem(foundItem);
						}
						else if (foundItem instanceof Relic)
						{
							((PhysicalItem) go).replace(currentRelic);
							equipItem(foundItem);
						}
						break;
					}
					if (go instanceof Consumable) 
					{ 
						Consumable c = (Consumable) go; 
						c.useItem();
						break;
					}
				}
			}
			lastPickup = System.currentTimeMillis();
		}
	}

	public void equipItem(Item item)
	{
		if(item instanceof OnHand)
		{
			if(onHandWeapon!=null)
				onHandWeapon.dequip();
			item.equip();
			onHandWeapon = (OnHand) item;

			((AnimationComponent) gc).updateSet(onHandWeapon.getType());
			GUIHandler.getGUIHandler().updateOnHand(onHandWeapon);
		}
		if(item instanceof Relic)
		{
			if(currentRelic!=null)
				currentRelic.dequip();
			item.equip();
			currentRelic = (Relic) item;
			GUIHandler.getGUIHandler().updateRelic(currentRelic);
		}
		
	}
	
	public void inputDone()
	{

		if (horizontalSums != 0 || verticalSums != 0)
		{
			
			if(((AnimationComponent) gc).isDone("attacking"))
			{
				gc.updateTexture("walking");
				actionPerformed(PlayerActions.WALK);
			}
			moveToPoint(x + horizontalSums, y + verticalSums, speed * DeltaTime.getDeltaTime().get());
			horizontalSums = verticalSums = 0;
			
		}
		else
		{
			
			if(((AnimationComponent) gc).getCurrentTrack().equals("walking"))
			{
				gc.updateTexture("default");
			}
		}
		

	}

	/*
	 * Called when the player clicks on the screen. Attacks if enough time has
	 * passed.
	 */
	public void attackLoc(int attX, int attY)
	{

		if (canAttack)
		{
			actionPerformed(PlayerActions.ATTACK);
			canAttack = false;
			double angle = Functions.angleMeasure((-Canvas.WIDTH / 2) + attX, (-Canvas.HEIGHT / 2) + attY);
			setRotation((int) angle);
			shouldRotate = false;
			gc.updateTexture("attacking");
			onHandWeapon.attack((float) angle,modAtt);
		}
	}

	/*
	 * Overrides the move command so that the cameras position also moves
	 */
	@Override
	public void move(double dx, double dy)
	{
		super.move(dx, dy);
		Camera.getCamera().setPos(x, y);
	}
	
	public float getModDef()
	{
		return modDef;
	}


	public void changeModDef(float amountToAdd)
	{
		this.modDef += amountToAdd;
	}


	public float getModAtt()
	{
		return modAtt;
	}


	public void changeModAtt(float amountToAdd)
	{
		this.modAtt += amountToAdd;
	}
	
	public void changeSpeed(float amountToAdd)
	{
		this.speed += amountToAdd;
	}

	public void takeDamage(float dam,EnemyObject source)
	{
		float moddedDamage = (dam - (modDef*dam));
		if(moddedDamage>0)
		{
			if(source!=null)
				AudioHandler.getAudioLibrary().playSoundEffect("pain");
			for(PlayerDamageListener pdl : damageListeners)
			{
				pdl.damageTaken(source, dam);
			}
			health -= moddedDamage;
	
			GUIHandler.getGUIHandler().updateHealth(health / maxHealth, poisonMagnitudes.size()>0);
	
			if (health <= 0)
			{
				die();
			}	
		}
	}


	public void die()
	{
		MainGame.getMainGame().changeLevel(Level.GAME_LOST);
	}

	public OnHand getOnHand()
	{
		return onHandWeapon;
	}

	public Relic getRelic()
	{
		return currentRelic;
	}

	public void pointCompassToBoss()
	{
		compassPointsToBoss = true;
	}


	public void incrementHealth(float healthToAdd)
	{
		health += healthToAdd;
		if(health>maxHealth)
			health = maxHealth;
		
		GUIHandler.getGUIHandler().updateHealth((float) health / (float) maxHealth, poisonMagnitudes.size()>0);
	}
	public float getHealth() 
	{ 
		return health; 
	}
	
	public enum PlayerActions
	{
		WALK,ATTACK,IDLE, PRAY
	}
	public void pray()
	{
		actionPerformed(PlayerActions.PRAY);
	}
	
	private void actionPerformed(PlayerActions action)
	{
		currentAction = action;
		for(PlayerListener l : listeners)
		{
			l.actionPerformed(action);
		}
	}

	/*
	 * @param poisonDamage The poisons damage per second
	 * @param time Time to apply the poison in seconds
	 */
	public void addPoison(float poisonDamage, float time)
	{
		poisonMagnitudes.add(poisonDamage);
		poisonTimes.add(time);		
		GUIHandler.getGUIHandler().updateHealth((float) health / (float) maxHealth, poisonMagnitudes.size()>0);
	}


	public PlayerActions getCurrentAction()
	{
		return currentAction;
	}
}
