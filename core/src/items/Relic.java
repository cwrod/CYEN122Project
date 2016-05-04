package items;

import gameObjects.EnemyObject;

public abstract class Relic extends Item
{

	public Relic(String ID, String flavorText, int x, int y)
	{
		super(x, y, ID, ID, flavorText);

	}

	public Relic(String ID, String flavorText)
	{
		super(ID, ID, flavorText);
	}

	
	protected int reduceDamage(int dam, float percentReduction)
	{
		return dam - (int)((float)dam*percentReduction);
	}
	


	public void update() 
	{
		
	}
}
