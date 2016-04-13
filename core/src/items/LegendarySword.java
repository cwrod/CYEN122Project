package items;

import gameObjects.Building;

public class LegendarySword extends OnHand
{
	public static final String ID = "legendarySword";
	public static final String FLAVOR = "This sword was the second shooter at the JFK assassination.";
	
	public static final int ATTACK_RANGE = 30;
	public static final int DAMAGE = 20;
	
	public LegendarySword(int x, int y, Building owner)
	{
		super(DAMAGE, ATTACK_RANGE, ID, FLAVOR, x, y);
	}

}
