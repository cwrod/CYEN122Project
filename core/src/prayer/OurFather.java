package prayer;

import gameObjects.PlayerObject;

public class OurFather extends Prayer
{
	public static final float COOLDOWN = 1.0f;
	
	public static final String ID = "ourFather";
	
	public OurFather()
	{
		super(COOLDOWN,ID);
	}
	@Override
	protected void doPrayer()
	{
		PlayerObject.getPlayerObject().incrementHealth(5);
	}
	@Override
	public String getID()
	{
		return ID;
	}

}
