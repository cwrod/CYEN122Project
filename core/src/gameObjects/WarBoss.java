package gameObjects;

import game.MainGame;
import game.MainGame.Level;
import gameObjects.PlayerObject.PlayerActions;
import quest.QuestHandler;
import toolbox.DeltaTime;

/*
 * Example child of enemyObject. This is probably not gonna make it to the final cut, 
 * but you should at least see how enemies are done.
 */
public class WarBoss extends Boss
{
	public static final int SIZE = 25;
	public static final String TEXTURE = "warBoss";
	public static final double SPEED = 50;
	public static final int DAMAGE = 10;
	public static final int MAX_RANGE = 40;
	public static final int MIN_RANGE = 30;
	public static final int ALERT_DISTANCE = 400;
	public static final int HEALTH = 150;
	

	public static final float IDLE_PENALTY = 5;
	
	
	public WarBoss(int xin, int yin, Building owner)
	{
		super(xin, yin, SIZE, SIZE, TEXTURE, SPEED, DAMAGE, MAX_RANGE,MIN_RANGE, ALERT_DISTANCE, HEALTH, owner);
		QuestHandler.getQuestHandler().setBoss(this);
	}
	
	@Override
	public void update()
	{
		super.update();
		if(owner.getPlayerIn())
		{
			if(PlayerObject.getPlayerObject().getCurrentAction()==PlayerActions.IDLE)
			{
				PlayerObject.getPlayerObject().takeDamage(IDLE_PENALTY*DeltaTime.getDeltaTime().get(),null);
			}
		}
	}
}
