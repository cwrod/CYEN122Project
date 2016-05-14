package gameObjects;

import graphics.Canvas.LayerType;

/*
 * Example child of enemyObject. This is probably not gonna make it to the final cut, 
 * but you should at least see how enemies are done.
 */
public class DemonDeath extends EnemyObject
{
	public static final int SIZE = 25;
	public static final String TEXTURE = "demonDeath";
	public static final double SPEED = 50;
	public static final int DAMAGE = 10;
	public static final int MAX_RANGE = 0;
	public static final int MIN_RANGE = 100;
	public static final int ALERT_DISTANCE = 400;
	public static final int HEALTH = 100;
	public static final float ATTACK_DELAY = 2.0f;
	public static final double PROJECTILE_SPEED = 300;
		
	
	
	
	
	public DemonDeath(int xin, int yin, Building owner)
	{
		super(xin, yin, SIZE, SIZE, TEXTURE, SPEED, DAMAGE, MAX_RANGE,MIN_RANGE, ALERT_DISTANCE, HEALTH, owner,ATTACK_DELAY);
	}

	@Override
	public void attack()
	{
		new Projectile(x,y,10,10,"arrow",LayerType.PLAYER,PROJECTILE_SPEED,(int)(DAMAGE),(int)rotation,this);
	}
}
