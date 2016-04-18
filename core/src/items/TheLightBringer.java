package items;

import gameObjects.Building;

public class TheLightBringer extends OnHand
{
	public static final String ID = "theLightBringer";
	public static final String TYPE = "sword";
	public static final String FLAVOR = "Have you ever held God's hand? this is as close as you can get";
	
	public static final int ATTACK_RANGE = 30;
	public static final int DAMAGE = 100;
	
	public TheLightBringer(int x, int y, Building owner)
	{
		super(DAMAGE, ATTACK_RANGE, ID, TYPE, FLAVOR, x, y);
	}
}
