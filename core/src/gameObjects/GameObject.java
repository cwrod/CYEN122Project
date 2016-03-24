package gameObjects;

import graphics.GraphicComponent;
import physics.Collider;
import physics.ColliderHandler;

public class GameObject {
	
	protected int x;
	protected int y;
	
	protected int xSize;
	protected int ySize;
	
	protected float rotation;
	
	protected boolean hittable;
	
	protected GraphicComponent gc;
	protected Collider c;


	public GameObject(int x, int y, int xSize, int ySize,String texture, int layer, boolean shouldCollide) 
	{
		this.x = x;
		this.y = y;
		this.xSize = xSize;
		this.ySize = ySize;
		this.rotation = 0;
		
		gc = new GraphicComponent(x,y,xSize,ySize,texture,layer);
		if(shouldCollide)
		{
		hittable = true;
		c = ColliderHandler.getColliderHandler().addCollider(this);
		}else{
		hittable = false;
		c = null;
		}
	}
	

	public int getX() {
		return x;
	}



	public int getY() {
		return y;
	}
	
	
	public void setPos(int xin,int yin)
	{
		x=xin;
		y=yin;
		gc.setPos(xin, yin);
		c.setPos(xin, yin);
	}


	public int getxSize() {
		return xSize;
	}

	public void setxSize(int xSize) {
		this.xSize = xSize;
	}

	public int getySize() {
		return ySize;
	}

	public void setySize(int ySize) {
		this.ySize = ySize;
	}

	public float getRotation() {
		return rotation;
	}

	public void setRotation(float rotation) {
		this.rotation = rotation;
	}

	

}
