package prayer;

import gameObjects.PlayerObject;

public class DeusVult extends Prayer
{
	public static final float COOLDOWN = 30.0f;
	public static final float DURATION = 10.0f;
	public static final float DAMAGE_MOD = 1.0f;
	public static final String ID = "deusVult";
	
	public DeusVult()
	{
		super(COOLDOWN, DURATION, ID);
	}

	@Override
	protected void doPrayer()
	{
		PlayerObject.getPlayerObject().changeModAtt(DAMAGE_MOD);
	}

	@Override
	public String getID()
	{
		return ID;
	}
	
	@Override
	protected void prayerEnd()
	{
		PlayerObject.getPlayerObject().changeModAtt(-DAMAGE_MOD);	
	}

}
