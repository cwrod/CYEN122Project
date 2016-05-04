package prayer;

import gameObjects.EnemyObject;
import gameObjects.PlayerDamageListener;
import gameObjects.PlayerListener;
import gameObjects.PlayerObject;
import gameObjects.PlayerObject.PlayerActions;

public class HolyRetribution extends Prayer implements PlayerDamageListener
{
	public static final float COOLDOWN = 30.0f;
	public static final float DURATION = 5.0f;
	public static final String ID = "holyRetribution";

	public static final float REFLECTION = 0.5f;

	public HolyRetribution()
	{
		super(COOLDOWN, DURATION, ID);
	}

	@Override
	protected void doPrayer()
	{
		PlayerObject.getPlayerObject().addDamageListener(this);
	}

	@Override
	public String getID()
	{
		return ID;
	}
	
	@Override
	protected void prayerEnd()
	{
		PlayerObject.getPlayerObject().removeDamageListener(this);
	}

	@Override
	public void damageTaken(EnemyObject source, float damage)
	{
		source.takeDamage(damage*REFLECTION);
		
	}



}
