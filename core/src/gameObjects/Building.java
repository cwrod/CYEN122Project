package gameObjects;

import java.util.ArrayList;

import ai.EnemyHandler;
import graphics.Canvas.LayerType;
import map.BuildingHandler;

public class Building extends GameObject
{

	
	private ArrayList<EnemyObject> enemies;
	private ArrayList<int[]> enemyOriginalXYCoords;
	
	private boolean isPlayerIn;
	
	public Building(int x, int y, int xSize, int ySize, String texture)
	{
		super(x, y, xSize, ySize, texture, LayerType.BUILDINGS, false, false);		
		
		enemies = new ArrayList<EnemyObject>();
		enemyOriginalXYCoords = new ArrayList<int[]>();
		isPlayerIn = false;
		
		BuildingHandler.getBuildingHandler().add(this);
	}
	
	public void addEnemy(EnemyObject eo)
	{
		enemies.add(eo);
		enemyOriginalXYCoords.add(new int[]{eo.getX(),eo.getY()});
	}
	
	public void update()
	{
		isPlayerIn = c.overlaps(PlayerObject.getPlayerObject().getCollider());
		if(isPlayerIn)
		{
			for(EnemyObject eo : enemies)
			{
				eo.update();
			}
		}
		else
		{
			for(int i = 0; i < enemies.size(); i++)
			{
				enemies.get(i).update(enemyOriginalXYCoords.get(i)[0],enemyOriginalXYCoords.get(i)[1]);
			}
		}
		
	}

	public void remove(EnemyObject enemyObject)
	{
		enemies.remove(enemyObject);
		if(enemies.size()==0)
		{
			BuildingHandler.getBuildingHandler().remove(this);
		}
	}

}
