package quest;

import java.util.ArrayList;

import gameObjects.Building;
import gameObjects.ClearDoor;

public class QuestHandler
{
	// Basic header for a singleton

	private static QuestHandler questHandlerSingleton;

	public static QuestHandler getQuestHandler()
	{
		if (questHandlerSingleton == null)
		{
			questHandlerSingleton = new QuestHandler();
		}

		return questHandlerSingleton;
	}

	private int goalNumber; // Number of buildings the player must clear
	private int currentCleared;

	private ArrayList<ClearDoor> clearDoors;

	private Building bossLair;

	public QuestHandler()
	{
		clearDoors = new ArrayList<ClearDoor>();
		currentCleared = 0;
		goalNumber = 1;
	}

	public void setGoalNumber(int goalNumber)
	{
		this.goalNumber = goalNumber;
	}

	public void buildingCleared()
	{
		currentCleared++;
		if (currentCleared == goalNumber)
		{
			for (ClearDoor c : clearDoors)
			{
				c.setUnlocked();
				bossLair.setActive(true);
			}
		}
	}

	public void addClearDoor(ClearDoor door)
	{
		clearDoors.add(door);
	}

	public void setBossLair(Building building)
	{
		bossLair = building;
	}
}
