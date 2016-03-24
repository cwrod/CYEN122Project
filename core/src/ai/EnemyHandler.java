package ai;

import java.util.ArrayList;

import gameObjects.EnemyObject;
import gameObjects.Goblin;

public class EnemyHandler {
	private ArrayList<EnemyObject> enemies;
	
	private static EnemyHandler enemyHandlerSingleton;
	
	public static EnemyHandler getEnemyHandler()
	{
		if(enemyHandlerSingleton==null)
		{
			enemyHandlerSingleton = new EnemyHandler();
		}
		return enemyHandlerSingleton;
	}
	
	public EnemyHandler()
	{
		enemies = new ArrayList<EnemyObject>();
	}
	
	public void spawnEnemies(EnemyObject eo)
	{
		enemies.add(eo);
	}
	public void update()
	{
		for(EnemyObject eo : enemies)
		{
			eo.update();
		}
	}

	
}
