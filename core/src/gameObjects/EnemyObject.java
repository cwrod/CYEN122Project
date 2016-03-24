package gameObjects;

import physics.ColliderHandler;
import toolbox.Functions;
import toolbox.Time;

public abstract class EnemyObject extends MobileGameObject
{
	protected double speed;
	protected int damage;
	protected float attackSpeed;
	protected float alertDistance;

	public EnemyObject(int xin, int yin, int xSize, int ySize, String texture, int layer) {
		super(xin, yin, xSize, ySize, texture, layer,true);
	}
	public void update()
	{
		CharacterObject co = CharacterObject.getCharacterObject();
		if(Functions.distance(this, co)<alertDistance){
			moveToPoint(co.getX(),co.getY());
		}
	}
	
	
	public void moveToPoint(int tarX, int tarY)
	{
		
	
		double [] sideRatios = Functions.sideRatios(tarX, tarY, x, y);
		
		moveHorizontally(speed*Time.getTime().getDeltaTime()*sideRatios[0]);
		moveVertically(speed*Time.getTime().getDeltaTime()*sideRatios[1]);
		
		
			
	}
}
