package items;

import gameObjects.Building;

public class HolyWater extends Relic
{
	public HolyWater(int x, int y, Building owner)
	{
		super(20, .1f, "holyWater","The Power of Christ compels you.", x, y);

	}

	public HolyWater()
	{
		super(20, .1f, "holyWater","The Power of Christ compels you.");
	}

}
