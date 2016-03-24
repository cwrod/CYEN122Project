package gameObjects;

public class Goblin extends EnemyObject
{

	public Goblin(int xin, int yin, int xSize, int ySize)
	{
		super(xin, yin, xSize, ySize, "goblin", 2);
		damage = 20;
		attackSpeed = 10;
		speed = 50;
		alertDistance = 200;
	}

}
