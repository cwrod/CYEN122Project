package gameObjects;

import java.util.ArrayList;

import game.MainGame;
import game.MainGame.Level;
import graphics.Canvas.LayerType;
import map.BuildingHandler;
import quest.QuestHandler;

public class Building extends GameObject
{

	private ArrayList<EnemyObject> enemies;
	private ArrayList<int[]> enemyOriginalXYCoords;

	private boolean isPlayerIn;
	private boolean isActive;
	
	private boolean isBossLair;

	public Building(int x, int y, int xSize, int ySize, String texture, boolean isActive)
	{
		super(x, y, xSize, ySize, texture, LayerType.BUILDINGS, false, false);

		enemies = new ArrayList<EnemyObject>();
		enemyOriginalXYCoords = new ArrayList<int[]>();
		isPlayerIn = false;
		this.isActive = isActive;
		isBossLair = !isActive;

		BuildingHandler.getBuildingHandler().add(this);
	}

	public void addEnemy(EnemyObject eo)
	{
		enemies.add(eo);
		enemyOriginalXYCoords.add(new int[] { eo.getX(), eo.getY() });
	}

	public boolean isActive()
	{
		return isActive;
	}

	public void update()
	{
		if (isActive)
		{
			isPlayerIn = c.overlaps(PlayerObject.getPlayerObject().getCollider());
			if (isPlayerIn)
			{
				for (EnemyObject eo : enemies)
				{
					eo.update();
				}
			}
			else
			{
				for (int i = 0; i < enemies.size(); i++)
				{
					enemies.get(i).update(enemyOriginalXYCoords.get(i)[0], enemyOriginalXYCoords.get(i)[1]);
				}
			}
		}
	}

	public void remove(EnemyObject enemyObject)
	{
		enemies.remove(enemyObject);
		if (enemies.size() == 0)
		{
			if(isBossLair)
			{
				MainGame.getMainGame().changeLevel(Level.GAME_WON);
			}
			QuestHandler.getQuestHandler().buildingCleared();
			BuildingHandler.getBuildingHandler().remove(this);
		}
	}

	public void setActive(boolean isActive)
	{
		this.isActive = isActive;
		for (EnemyObject eo : enemies)
		{
			eo.setActive(isActive);
		}
	}

}
