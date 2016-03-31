package gameObjects;


/*
 * Example child of enemyObject. This is probably not gonna make it to the final cut, 
 * but you should at least see how enemies are done.
 */
public class Goblin extends EnemyObject
{

	public Goblin(int xin, int yin, int xSize, int ySize)
	{
		super(xin, yin, xSize, ySize, "goblin", 50, 2, .5f, 50, 400, 100);

	}

}
