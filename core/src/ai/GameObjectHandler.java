package ai;

import java.util.ArrayList;

import gameObjects.GameObject;

/*
 * Singleton class that calls update on all enemies. New enemies should be spawned with the spawned 
 * with the spawnEnemies method.
 */
public class GameObjectHandler
{

	// Basic Singleton header

	private static GameObjectHandler gameObjectHandlerSingleton;

	public static GameObjectHandler getEnemyHandler()
	{
		if (gameObjectHandlerSingleton == null)
		{
			gameObjectHandlerSingleton = new GameObjectHandler();
		}
		return gameObjectHandlerSingleton;
	}

	public static void reset()
	{
		gameObjectHandlerSingleton = new GameObjectHandler();
	}

	private ArrayList<GameObject> things;
	
	private ArrayList<GameObject> killList;
	public GameObjectHandler()
	{
		things = new ArrayList<GameObject>();
		killList = new ArrayList<GameObject>();
		
		
	}

	/*
	 * Adds the enemy to the scene. When we actually handle spawning, this will
	 * most likely have to change.
	 */
	public void add(GameObject eo)
	{
		things.add(eo);
		
	} 
	
	
	
	
	/*
	 * Called from the render class. Updates all enemies.
	 */
	public void update()
	{
		for (GameObject go : things)
		{
			go.update();
		}
		things.removeAll(killList);
	}

	/*
	 * Takes out an enemy (like when they die.)
	 */
	public void remove(GameObject gameObject)
	{
		killList.add(gameObject);

	}

}
