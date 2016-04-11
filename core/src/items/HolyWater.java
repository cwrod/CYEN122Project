package items;

import gameObjects.Building;
import gameObjects.EnemyObject;

public class HolyWater extends Relic
{
	public static final String ID = "holyWater";
	public static final String FLAVOR = "The Power of Christ compels you.\nReduces damage by 10 percent.";
	
	public static final float ABSORBTION = 0.1f;
	
	public HolyWater(int x, int y, Building owner)
	{
		super(ID, FLAVOR, x, y);

	}

	public HolyWater()
	{
		super(ID,FLAVOR);
	}

	@Override
	public int defend(int dam, EnemyObject source)
	{
		return reduceDamage(dam,ABSORBTION);
	}

}
