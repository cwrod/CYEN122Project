package gameObjects;

public class Elixr extends Consumable 
{
	public Elixr(int x, int y, Building owner) 
	{
		super(x, y, "elixr");
	}

	public void onItemUse() 
	{
		PlayerObject.getPlayerObject().incrementHealth(100);
		this.kill();
	}
}
