package gameObjects;

import com.badlogic.gdx.Gdx;

import ai.EnemyHandler;
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

	public EnemyObject(int xin, int yin, int xSize, int ySize, String texture, double speedIn, int damageIn,
			float attackMaxRangeIn, float alertDistanceIn, int healthIn)
	{
		super(xin, yin, xSize, ySize, texture, 3, true, true);
		speed = speedIn;
		damage = damageIn;
		attackMinRange = 30;
		attackMaxRange = attackMaxRangeIn;
		alertDistance = alertDistanceIn;
		health = healthIn;

		isAttacking = false;
		EnemyHandler.getEnemyHandler().add(this);
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
			PlayerObject.getPlayerObject().takeDamage(damage);
		}
	}

	/*
	 * When the character hits the enemy, this is called
	 */
	public void takeDamage(int dam)
	{
		health -= dam;
		if (health <= 0)
		{
			die();
		}
	}

	/*
	 * When enemies go down. TODO: death animations and kill collider (or
	 * preferably make it non-hittable).
	 */
	public void die()
	{
		EnemyHandler.getEnemyHandler().remove(this);
	}

}
