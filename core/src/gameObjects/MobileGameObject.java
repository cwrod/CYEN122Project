package gameObjects;

import graphics.Camera;
import graphics.GraphicComponent;

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

		
		if(dx*movementX<0){
			movementX=0;
		}
		movementX+=dx;
		if(movementX>1.0 || movementX<-1.0)
		{
		x+=movementX-(movementX%1);

		movementX-=movementX-(movementX%1);

		}
		gc.setPos(x, y);
	}
	
	public void moveVertically(double dy)
	{
		
		if(dy*movementY<0){
			movementY=0;
		}
		movementY+=dy;
		if(movementY>1.0 || movementY<-1.0)
		{
		y+=movementY-(movementY%1);
		movementY-=movementY-(movementY%1);
		}
		
		gc.setPos(x, y);
	}
	
	public void setRotation(float newRot)
	{
		rotation = newRot;
	}
}
