package gameObjects;

import ai.EnemyHandler;
import toolbox.Functions;
import toolbox.Time;

/*
 * Abstract class which all enemies should inherit from (maybe not bosses, we'll see)
 */
public abstract class EnemyObject extends MobileGameObject
{
	protected double speed;
	protected int damage;
	
	protected float attackSpeed;
	protected float attackWait;
	
	protected float attackMinRange;
	protected float attackMaxRange;
	
	
	protected boolean isAttacking;
	
	protected float alertDistance;
	
	protected int health;


	public EnemyObject(int xin, int yin, int xSize, int ySize, String texture, double speedIn, 
			int damageIn, float attackSpeedIn, float attackMinRangeIn, float alertDistanceIn, int healthIn) 
	{
		super(xin, yin, xSize, ySize, texture, 2,true,false);
		speed = speedIn;
		damage = damageIn;
		attackSpeed = attackSpeedIn;
		attackMinRange = attackMinRangeIn;
		attackMaxRange = attackMinRange + 5;
		alertDistance = alertDistanceIn;
		health = healthIn;
		
		attackWait = 0;
		isAttacking = false;
	}
	
	/*
	 * Basic chase function that most enemies should inherit.
	 * This can be overrided if some enemies should do something else.
	 */
	public void update()
	{
		PlayerObject po = PlayerObject.getPlayerObject();
		
		if(isAttacking)
		{
			attackWait+=Time.getTime().getDeltaTime();
			if(attackWait>attackSpeed)
			{
				attack(Functions.distance(this, po)<attackMaxRange);
				attackWait = 0;
				isAttacking = false;
			}
		}
		else
		{
			if(Functions.distance(this, po)<alertDistance){
				if(Functions.distance(this, po)<attackMinRange)
				{
					isAttacking = true;
	
				}
				else
				{
					moveToPoint(po.getX(),po.getY(),speed*Time.getTime().getDeltaTime());
			
				}
			}
		}
	}
	
	/*
	 * Doesn't do much now, but this is probably where I'll
	 * stuff the animation stuff. Also important if enemies
	 * do something weird while attacking.
	 */
	public void attack(boolean willHit) 
	{
		if(willHit)
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
		if(health <= 0)
		{
			die();
		}
	}
	
	
	/*
	 * When enemies go down.
	 * TODO: death animations and kill collider (or preferably make it non-hittable).
	 */
	public void die()
	{
		EnemyHandler.getEnemyHandler().remove(this);
	}
	
	
}
