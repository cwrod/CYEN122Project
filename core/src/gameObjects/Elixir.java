package gameObjects;

public class Elixir extends Consumable 
{
	public Elixir(int x, int y, String texture) 
	{
		super(x, y, texture);
	}

	public void useItem() 
	{
		PlayerObject.getPlayerObject().incrementHealth(100);
	}
}
