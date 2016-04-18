package items;

import gameObjects.Building;

public class BladeOfWar extends OnHand
{
	public static final String ID = "bladeOfWar";
	public static final String TYPE = "sword";
	public static final String FLAVOR = "After killing war, this was all that remains of him";
	
	public static final int ATTACK_RANGE = 30;
	public static final int DAMAGE = 70;
	
	public BladeOfWar(int x, int y, Building owner)
	{
		super(DAMAGE, ATTACK_RANGE, ID, TYPE, FLAVOR, x, y);
	}
}
