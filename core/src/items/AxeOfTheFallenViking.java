package items;

import gameObjects.Building;

public class AxeOfTheFallenViking extends OnHand
{
	public static final String ID = "axeOfTheFallenViking";
	public static final String TYPE = "sword";
	public static final String FLAVOR = "The name says it all.";
	
	public static final int ATTACK_RANGE = 25;
	public static final int DAMAGE = 55;
	
	public AxeOfTheFallenViking(int x, int y, Building owner)
	{
		super(DAMAGE, ATTACK_RANGE, ID, TYPE, FLAVOR, x, y);
	}
	public AxeOfTheFallenViking()
	{
		super(DAMAGE, ATTACK_RANGE, ID, TYPE, FLAVOR);
	}

}
