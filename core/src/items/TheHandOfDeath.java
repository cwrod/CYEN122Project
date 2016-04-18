package items;

import gameObjects.Building;

public class TheHandOfDeath extends OnHand
{
	public static final String ID = "theHandOfDeath";
	public static final String TYPE = "sword";
	public static final String FLAVOR = "You have overpowered death, now you are the bringer of death";
	
	public static final int ATTACK_RANGE = 40;
	public static final int DAMAGE = 90;
	
	public TheHandOfDeath(int x, int y, Building owner)
	{
		super(DAMAGE, ATTACK_RANGE, ID, TYPE, FLAVOR, x, y);
	}
}
