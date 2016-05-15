package gameObjects;

/*
 * Example child of enemyObject. This is probably not gonna make it to the final cut, 
 * but you should at least see how enemies are done.
 */
public class DemonPlague extends EnemyObject
{
	public static final int SIZE = 25;
	public static final String TEXTURE = "demonPlague";
	public static final double SPEED = 50;
	public static final int DAMAGE = 15;
	public static final int MAX_RANGE = 40;
	public static final int MIN_RANGE = 30;
	public static final int ALERT_DISTANCE = 400;
	public static final int HEALTH = 60;
	

	public static final int POISON_DAMAGE = 4;
	public static final float POISON_DURATION = 5.0f;
	
	
	
	public DemonPlague(int xin, int yin, Building owner)
	{
		super(xin, yin, SIZE, SIZE, TEXTURE, SPEED, DAMAGE, MAX_RANGE,MIN_RANGE, ALERT_DISTANCE, HEALTH, owner);
	}

	@Override
	protected void dealDamage()
	{
		super.dealDamage();
		PlayerObject.getPlayerObject().addPoison(POISON_DAMAGE, POISON_DURATION);
	}
}
