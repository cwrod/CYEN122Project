package gameObjects;

import graphics.Camera;
import graphics.GraphicComponent;
import physics.ColliderHandler;

public class MobileGameObject extends GameObject
{


	
	private double movementX;
	private double movementY;
	
	public MobileGameObject(int xin, int yin,int xSize, int ySize,String texture, int layer, boolean hittable)
	{
		super(xin, yin, xSize, ySize, texture, layer, hittable);

		movementX = movementY = 0;
	}
	


	public void moveHorizontally(double dx)
	{

		int changeX = 0;
		if(dx*movementX<0){
			movementX=0;
		}
		movementX+=dx;
		if(movementX>1.0 || movementX<-1.0)
		{
		changeX+=movementX-(movementX%1);

		movementX-=movementX-(movementX%1);

		}
		if(!ColliderHandler.getColliderHandler().checkForCollisions(c, changeX, 0)){
		setPos(x+changeX,y);
		}
	}
	
	public void moveVertically(double dy)
	{
		int changeY = 0;
		if(dy*movementY<0){
			movementY=0;
		}
		movementY+=dy;
		if(movementY>1.0 || movementY<-1.0)
		{
		changeY+=movementY-(movementY%1);
		movementY-=movementY-(movementY%1);
		}
		if(!ColliderHandler.getColliderHandler().checkForCollisions(c,0, changeY)){
		setPos(x,y+changeY);
		}else{
			movementY=0;
		}
	}
	
	public void setRotation(float newRot)
	{
		rotation = newRot;
	}
}
