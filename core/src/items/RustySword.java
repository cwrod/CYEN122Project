package items;

import gameObjects.Building;

public class RustySword extends OnHand
{

	public static final String ID = "rustySword";
	public static final String TYPE = "sword";
	public static final String FLAVOR = "The sword version of a Tyler Perry movie.";
	
	public static final int ATTACK_RANGE = 30;
	public static final int DAMAGE = 50;
	
	
	
	public RustySword(int x, int y, Building owner)
	{
		super(DAMAGE, ATTACK_RANGE, ID, TYPE, FLAVOR, x, y);

	}

	public RustySword()
	{
		super(DAMAGE, ATTACK_RANGE, ID, TYPE, FLAVOR);
	}

}
