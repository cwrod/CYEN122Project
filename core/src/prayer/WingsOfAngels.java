package prayer;

import gameObjects.PlayerObject;

public class WingsOfAngels extends Prayer
{
	public static final float COOLDOWN = 20.0f;
	public static final float DURATION = 10.0f;
	public static final float SPEED_MOD = 30.0f;
	public static final String ID = "wingsOfAngels";
	
	public WingsOfAngels()
	{
		super(COOLDOWN, DURATION, ID);
	}

	@Override
	protected void doPrayer()
	{
		PlayerObject.getPlayerObject().changeModDef(SPEED_MOD);
	}

	@Override
	public String getID()
	{
		return ID;
	}
	
	@Override
	protected void prayerEnd()
	{
		PlayerObject.getPlayerObject().changeModDef(-SPEED_MOD);	
	}

}
