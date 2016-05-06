package gameObjects;

import audio.AudioHandler;
import game.MainGame;
import quest.QuestHandler;

public abstract class Boss extends EnemyObject
{

	public Boss(int xin, int yin, int xSize, int ySize, String texture, double speedIn, int damageIn,
			int attackMaxRangeIn, int attackMinRangeIn, float alertDistanceIn, int healthIn, Building owner)
	{
		super(xin, yin, xSize, ySize, texture, speedIn, damageIn, attackMaxRangeIn, attackMinRangeIn, alertDistanceIn, healthIn,
				owner);
		QuestHandler.getQuestHandler().setBoss(this);
	}
	
	@Override
	public void die()
	{
		MainGame.getMainGame().incrementLevel();
	}
	
	@Override
	protected void playAttackSound()
	{
		AudioHandler.getAudioLibrary().playSoundEffect("bossTaunt");
	}

}
