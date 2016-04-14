package items;

import gameObjects.Building;

public class Rosary extends Relic {
	public static final String ID = "rosary";
	public static final String FLAVOR = "Mary watches over you.\nReduces damage by 20 percent.";
	
	
	public Rosary(int x, int y, Building owner)
	{
		super(ID, FLAVOR, x, y);

	}

	public void update()
	{
		
	}

}
