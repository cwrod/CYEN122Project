package items;

import gameObjects.Building;

public class IronSword extends OnHand
{
	public static final String ID = "ironSword";
	public static final String TYPE = "sword";
	public static final String FLAVOR = "This sword is slightly better than a hot dog";
	
	public static final int ATTACK_RANGE = 30;
	public static final int DAMAGE = 20;

public IronSword(int x, int y, Building owner)
{
	super(DAMAGE, ATTACK_RANGE, ID, TYPE, FLAVOR, x, y);
}

}
