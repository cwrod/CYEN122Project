package gameObjects;

import game.MainGame;
import game.MainGame.Level;
import gameObjects.PlayerObject.PlayerActions;
import quest.QuestHandler;

/*
 * Example child of enemyObject. This is probably not gonna make it to the final cut, 
 * but you should at least see how enemies are done.
 */
public class FamineBoss extends Boss implements PlayerListener
{
	public static final int SIZE = 25;
	public static final String TEXTURE = "famineBoss";
	public static final double SPEED = 50;
	public static final int DAMAGE = 10;
	public static final int MAX_RANGE = 40;
	public static final int MIN_RANGE = 30;
	public static final int ALERT_DISTANCE = 400;
	public static final int HEALTH = 150;
	

	public static final int ATTACK_PENALTY = 5;
	
	
	public FamineBoss(int xin, int yin, Building owner)
	{
		super(xin, yin, SIZE, SIZE, TEXTURE, SPEED, DAMAGE, MAX_RANGE,MIN_RANGE, ALERT_DISTANCE, HEALTH, owner);
		QuestHandler.getQuestHandler().setBoss(this);
	}
	
	@Override
	public void actionPerformed(PlayerActions action)
	{
		if(owner.getPlayerIn())
		{
			if(action == PlayerActions.ATTACK)
			{
				PlayerObject.getPlayerObject().takeDamage(ATTACK_PENALTY, this);
			}	
		}
	}
}
