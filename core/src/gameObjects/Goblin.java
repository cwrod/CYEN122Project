package gameObjects;

/*
 * Example child of enemyObject. This is probably not gonna make it to the final cut, 
 * but you should at least see how enemies are done.
 */
public class Goblin extends EnemyObject
{

	public Goblin(int xin, int yin, Building owner)
	{
		super(xin, yin, 25, 25, "goblin", 50, 2, 25, 400, 100, owner);

	}

}
