package gameObjects;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;

import ai.EnemyHandler;
import graphics.Canvas.LayerType;
import graphics.GraphicComponent;
import graphics.TempComponent;
import toolbox.Functions;

/*
 * Abstract class which all enemies should inherit from (maybe not bosses, we'll see)
 */
public abstract class EnemyObject extends MobileGameObject
{
	protected double speed;
	protected int damage;

	protected float attackMinRange;
	protected float attackMaxRange;

	protected boolean isAttacking;

	protected float alertDistance;

	protected int health;
	protected int maxHealth;
	

	protected ArrayList<GraphicComponent> healthSigns;

	protected Building owner;

	protected boolean isActive;

	public EnemyObject(int xin, int yin, int xSize, int ySize, String texture, double speedIn, int damageIn,
			int attackMaxRangeIn, int attackMinRangeIn, float alertDistanceIn, int healthIn, Building owner)
	{
		super(xin, yin, xSize, ySize, texture, LayerType.ENEMIES, true, true);
		speed = speedIn;
		damage = damageIn;
		attackMaxRange = attackMaxRangeIn;
		attackMinRange = attackMinRangeIn;
		alertDistance = alertDistanceIn;
		health = maxHealth = healthIn;

		isAttacking = false;

		this.owner = owner;
		if (owner == null)
		{
			EnemyHandler.getEnemyHandler().add(this);
			isActive = true;
		}
		else
		{
			owner.addEnemy(this);
			isActive = owner.isActive();
		}
	}

	public void setActive(boolean isActive)
	{
		this.isActive = isActive;
	}

	/*
	 * Basic chase function that most enemies should inherit. This can be
	 * overridden if some enemies should do something else.
	 */
	public void update()
	{
		PlayerObject po = PlayerObject.getPlayerObject();

		if (isAttacking)
		{

			if (gc.isDone("attacking"))
			{
				attack();
				isAttacking = false;
			}
		}
		else
		{
			if (Functions.distance(this, po) < alertDistance)
			{
				if (Functions.distance(this, po) < attackMinRange)
				{

					turnTo(po.getX(), po.getY());
					gc.updateTexture("attacking");
					isAttacking = true;

				}
				else
				{
					moveToPoint(po.getX(), po.getY(), speed * Gdx.graphics.getDeltaTime());

				}
			}
		}
	}

	public void update(int x, int y)
	{
		if (this.x != x || this.y != y)
		{
			moveToPoint(x, y, speed * Gdx.graphics.getDeltaTime());
		}
	}

	/*
	 * Doesn't do much now, but this is probably where I'll stuff the animation
	 * stuff. Also important if enemies do something weird while attacking.
	 */
	public void attack()
	{
		PlayerObject po = PlayerObject.getPlayerObject();

		int attackPointX = x + (xSize / 2) + (int) (Math.cos(Math.toRadians(rotation)) * attackMaxRange);
		int attackPointY = y + (ySize / 2) + (int) (Math.sin(Math.toRadians(rotation)) * attackMaxRange);

		if (po.getCollider().willCollide(attackPointX, attackPointY))
		{
			PlayerObject.getPlayerObject().takeDamage(damage, this);
		}
	}

	/*
	 * @Override public void setPos(int x, int y) { super.setPos(x,y);
	 * if(healthSigns.size()>0) { for(GraphicComponent gc : healthSigns) {
	 * gc.setPos(x, y); } }
	 * 
	 * }
	 */
	/*
	 * When the character hits the enemy, this is called
	 */
	public void takeDamage(int dam)
	{
		if (isActive)
		{
			health -= dam;
			if (health <= 0)
			{
				die();
			}
			else
			{
				makeHealthSigns();
			}
		}
	}

	private void makeHealthSigns()
	{

		addLabel(new TempComponent(x, y, xSize, 5, 0, -10, 1.0f, "healthLostBar", LayerType.EFFECTS));
		addLabel(new TempComponent(x, y, (int) (((float)health / (float)maxHealth) * xSize), 5, 0, -10, 1.0f, "healthBar",
				LayerType.EFFECTS));

	}

	/*
	 * When enemies go down. TODO: death animations and kill collider (or
	 * preferably make it non-hittable).
	 */
	public void die()
	{
		if (owner == null)
		{
			EnemyHandler.getEnemyHandler().remove(this);
		}
		else
		{
			owner.remove(this);
		}
		c.setHittable(false);
	}
}
