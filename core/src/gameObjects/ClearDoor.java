package gameObjects;

import quest.QuestHandler;

public class ClearDoor extends Door
{

	public ClearDoor(int x, int y, Building b)
	{
		super(x, y);
		QuestHandler.getQuestHandler().addClearDoor(this);
	}

}
