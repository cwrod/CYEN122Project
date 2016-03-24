package gameObjects;

import graphics.Camera;
import graphics.Canvas;
import graphics.GraphicComponent;
import physics.ColliderHandler;
import toolbox.Time;

public class CharacterObject extends MobileGameObject{

	
	
	private float speed;
	
	private static CharacterObject characterObjectSingleton;
	
	public static CharacterObject getCharacterObject()
	{
		if(characterObjectSingleton==null)
		{
			characterObjectSingleton = new CharacterObject();
		}
		return characterObjectSingleton;
	}
	
	public CharacterObject()
	{
		super(400,400,25,25,"person",2,true);
		Camera.getCamera().setPos(x, y);
		speed = 80;
	}
	public void moveLeft()
	{
		moveHorizontally(-speed*Time.getTime().getDeltaTime());
	}
	public void moveRight()
	{
		moveHorizontally(speed*Time.getTime().getDeltaTime());
	}
	public void moveUp()
	{
		moveVertically(speed*Time.getTime().getDeltaTime());
	}public void moveDown()
	{
		moveVertically(-speed*Time.getTime().getDeltaTime());
	}


	
	
	public void moveHorizontally(double dx)
	{
		super.moveHorizontally(dx);
		Camera.getCamera().setPos(x, y);
	}
	
	public void moveVertically(double dy)
	{
		super.moveVertically(dy);
		Camera.getCamera().setPos(x, y);
	}
	
}
