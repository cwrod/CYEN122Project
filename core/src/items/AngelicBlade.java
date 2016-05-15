package items;

import gameObjects.Building;

public class AngelicBlade extends OnHand {
	public static final String ID = "angelicBlade";
	public static final String TYPE = "sword";
	public static final String FLAVOR = "You found this blade next to a dead angel, he went out fighting.";
	
	public static final int ATTACK_RANGE = 30;
	public static final int DAMAGE = 65;
	
	public AngelicBlade(int x, int y, Building owner)
	{
		super(DAMAGE, ATTACK_RANGE, ID, TYPE, FLAVOR, x, y);
	}
	public AngelicBlade()
	{
		super(DAMAGE, ATTACK_RANGE, ID, TYPE, FLAVOR);
	}

}
