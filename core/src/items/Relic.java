package items;

import java.util.ArrayList;

import ai.EnemyHandler;
import gameObjects.EnemyObject;
import gameObjects.GameObject;
import gameObjects.PhysicalItem;
import gameObjects.PlayerObject;
import physics.ColliderHandler;


public abstract class Relic 
{
	protected float defenseBarrier; // size of the defense 
	protected int absorbance; //how much damage that can be absorbed 
	private String relicName;
	
	public Relic(int absorbance, float defenseBarrier, String relicName, int x, int y)
	{
		this.absorbance = absorbance;
		this.defenseBarrier = defenseBarrier;
		this.relicName = relicName;
		new PhysicalItem(x, y, relicName, this)
		
	}
	
	public Relic(int absorbance, float defenseBarrier, String relicName)
	{
		this.absorbance = absorbance;
		this.defenseBarrier = defenseBarrier;
		this.relicName = relicName;
	}
	
	public void defend()
	{
		
	}
	
	public String getRelicName()
	{
		return relicName;
	}
}
