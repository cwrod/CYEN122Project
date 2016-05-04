package prayer;

import gameObjects.PlayerObject;

public class TueriCorpus extends Prayer
{
	public static final float COOLDOWN = 30.0f;
	public static final float DURATION = 5.0f;
	public static final float DEF_MOD = 1.0f;
	public static final String ID = "tueriCorpus";
	
	public TueriCorpus()
	{
		super(COOLDOWN, DURATION, ID);
	}

	@Override
	protected void doPrayer()
	{
		PlayerObject.getPlayerObject().changeModDef(DEF_MOD);
	}

	@Override
	public String getID()
	{
		return ID;
	}
	
	@Override
	protected void prayerEnd()
	{
		PlayerObject.getPlayerObject().changeModDef(-DEF_MOD);	
	}

}
