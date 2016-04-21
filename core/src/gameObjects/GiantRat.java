package gameObjects;

/*
 * Example child of enemyObject. This is probably not gonna make it to the final cut, 
 * but you should at least see how enemies are done.
 */
public class GiantRat extends EnemyObject
{
	public static final int SIZE = 20;
	public static final String TEXTURE = "giantRat";
	public static final double SPEED = 70;
	public static final int DAMAGE = 15;
	public static final int MAX_RANGE = 40;
	public static final int MIN_RANGE = 30;
	public static final int ALERT_DISTANCE = 400;
	public static final int HEALTH = 80;
	
	
	
	
	public GiantRat(int xin, int yin, Building owner)
	{
		super(xin, yin, SIZE, SIZE, TEXTURE, SPEED, DAMAGE, MAX_RANGE,MIN_RANGE, ALERT_DISTANCE, HEALTH, owner);
	}

}
