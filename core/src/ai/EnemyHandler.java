package ai;

import java.util.ArrayList;

import gameObjects.EnemyObject;

/*
 * Singleton class that calls update on all enemies. New enemies should be spawned with the spawned 
 * with the spawnEnemies method.
 */
public class EnemyHandler
{

	// Basic Singleton header

	private static EnemyHandler enemyHandlerSingleton;

	public static EnemyHandler getEnemyHandler()
	{
		if (enemyHandlerSingleton == null)
		{
			enemyHandlerSingleton = new EnemyHandler();
		}
		return enemyHandlerSingleton;
	}

	public static void reset()
	{
		enemyHandlerSingleton = new EnemyHandler();
	}

	private ArrayList<EnemyObject> enemies;

	public EnemyHandler()
	{
		enemies = new ArrayList<EnemyObject>();
	}

	/*
	 * Adds the enemy to the scene. When we actually handle spawning, this will
	 * most likely have to change.
	 */
	public void add(EnemyObject eo)
	{
		enemies.add(eo);
	}

	/*
	 * Called from the render class. Updates all enemies.
	 */
	public void update()
	{
		for (EnemyObject eo : enemies)
		{
			eo.update();
		}
	}

	/*
	 * Takes out an enemy (like when they die.)
	 */
	public void remove(EnemyObject enemyObject)
	{
		enemies.remove(enemyObject);
	}

}
