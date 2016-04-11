package items;

import gameObjects.EnemyObject;

public abstract class Relic extends Item
{

	public Relic(String ID, String flavorText, int x, int y)
	{
		super(x, y, ID, flavorText);

	}

	public Relic(String ID, String flavorText)
	{
		super(ID, flavorText);
	}

	public abstract int defend(int dam, EnemyObject source);
	
	protected int reduceDamage(int dam, float percentReduction)
	{
		return dam - (int)((float)dam*percentReduction);
	}
}
