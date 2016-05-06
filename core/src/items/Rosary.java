package items;

import gameObjects.Building;
import gameObjects.PlayerObject;
import toolbox.DeltaTime;

public class Rosary extends Relic {
	public static final String ID = "rosary";
	public static final String FLAVOR = "Catholics, amiright?\nGradual Heal";
	
	public static final float HEALTH_PER_SECOND = 1.0f;
	
	public Rosary(int x, int y, Building owner)
	{
		super(ID, FLAVOR, x, y);

	}

	
	public Rosary()
	{
		super(ID, FLAVOR);
	}


	public void update()
	{
		PlayerObject.getPlayerObject().incrementHealth(HEALTH_PER_SECOND*DeltaTime.getDeltaTime().get());
	}

}
