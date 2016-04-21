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
	private ArrayList<EnemyObject> handledEnemies;
	
	private ArrayList<EnemyObject> killList;
	public EnemyHandler()
	{
		enemies = new ArrayList<EnemyObject>();
		handledEnemies = new ArrayList<EnemyObject>();
		killList = new ArrayList<EnemyObject>();
		
		
	}

	/*
	 * Adds the enemy to the scene. When we actually handle spawning, this will
	 * most likely have to change.
	 */
	public void add(EnemyObject eo)
	{
		enemies.add(eo);
		handledEnemies.add(eo);
		
	} 
	
	public void addHandledEnemy(EnemyObject eo)
	{
		handledEnemies.add(eo);
	}
	public ArrayList<EnemyObject> getEnemies()
	{
		return handledEnemies;
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
		handledEnemies.removeAll(killList);
		enemies.removeAll(killList);
		killList.clear();
	}

	/*
	 * Takes out an enemy (like when they die.)
	 */
	public void remove(EnemyObject enemyObject)
	{
		killList.add(enemyObject);

	}

}
