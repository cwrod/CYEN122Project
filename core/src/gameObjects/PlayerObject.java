package gameObjects;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;

import graphics.Camera;
import graphics.Canvas;
import graphics.Canvas.LayerType;
import graphics.GraphicComponent;
import gui.GUIComponent;
import gui.GUIHandler;
import items.HolyWater;
import items.Item;
import items.OnHand;
import items.Relic;
import items.RustySword;
import physics.ColliderHandler;
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

	private float speed;
	private int health;
	private int maxHealth;

	private OnHand onHandWeapon;
	private Relic currentRelic;
	
	private boolean canAttack;

	public PlayerObject()
	{
		super(400, 400, WIDTH, HEIGHT, "player", LayerType.PLAYER, true, true);
		Camera.getCamera().setPos(x, y);
		speed = 80;
		health = maxHealth = 100;
		canAttack = true;
		onHandWeapon = new RustySword();
		currentRelic = new HolyWater();
		gc.updateSet(onHandWeapon.getID());
	}

	public void update()
	{
		if (gc.isDone("attacking"))
		{
			shouldRotate = true;
			canAttack = true;
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
				GameObject go = goList.get(0);
					if (go instanceof PhysicalItem)
					{
						Item foundItem =  ((PhysicalItem) go).getAssociatedItem();
						if(foundItem instanceof OnHand)
						{
							OnHand temp = onHandWeapon;
							onHandWeapon = (OnHand) foundItem;
							gc.updateSet(onHandWeapon.getID());
							((PhysicalItem) go).replace(temp);
							GUIHandler.getGUIHandler().updateOnHand(onHandWeapon);
						}
						else if(foundItem instanceof Relic)
						{
							Relic temp = currentRelic;
							currentRelic = (Relic) foundItem;
							((PhysicalItem) go).replace(temp);
							GUIHandler.getGUIHandler().updateRelic(currentRelic);
						}
					}
			}
			lastPickup = System.currentTimeMillis();
		}
	}

	public void inputDone()
	{

		if (horizontalSums != 0 || verticalSums != 0)
		{
			moveToPoint(x + horizontalSums, y + verticalSums, speed * Gdx.graphics.getDeltaTime());
			horizontalSums = verticalSums = 0;
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
			canAttack = false;
			double angle = Functions.angleMeasure((-Canvas.WIDTH / 2) + attX, (Canvas.HEIGHT / 2) - attY);
			setRotation((int) angle);
			shouldRotate = false;
			gc.updateTexture("attacking");
			onHandWeapon.attack((float) angle);
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

	/*
	 * Exact same as enemyObject takeDamage.
	 * 
	 * This kinda makes me want both the player object and the enemyObject to
	 * inherit from an abstract character object class, but I guess this is fine
	 * for now.
	 */
	public void takeDamage(int dam)
	{
		health -= dam;

		GUIHandler.getGUIHandler().updateHealth((float) health / (float) maxHealth);

		if (health <= 0)
		{
			die();
		}
	}

	/*
	 * TODO: make this actually do something.
	 */
	public void die()
	{
		System.out.println("GameOver");
	}

	public OnHand getOnHand()
	{
		return onHandWeapon;
	}
	public Relic getRelic()
	{
		return currentRelic;
	}
	

}
