package prayer;

import gameObjects.PlayerObject;

public class OurFather extends Prayer
{
	public static final float COOLDOWN = 1.0f;
	public static final String ICON = "ourFatherIcon";
	
	public OurFather()
	{
		super(COOLDOWN,ICON);
	}
	@Override
	protected void doPrayer()
	{
		PlayerObject.getPlayerObject().incrementHealth(5);
	}

}
