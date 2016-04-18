package items;

import gameObjects.Building;

public class SkeletonsArm extends OnHand
{
	public static final String ID = "skeletonsArm";
	public static final String TYPE = "sword";
	public static final String FLAVOR = "You ripped this off of a skeleton and beat him with it, it is yours now";
	
	public static final int ATTACK_RANGE = 25;
	public static final int DAMAGE = 30;
	
	public SkeletonsArm(int x, int y, Building owner)
	{
		super(DAMAGE, ATTACK_RANGE, ID, TYPE, FLAVOR, x, y);
	}
}
