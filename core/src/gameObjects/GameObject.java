package gameObjects;

import java.util.ArrayList;

import graphics.AnimationComponent;
import graphics.Canvas.LayerType;
import graphics.GraphicComponent;
import physics.Collider;
import physics.ColliderHandler;
import toolbox.Functions;

/*
 * If there is an object you can physically see in the game, it probably should come from this.
 * Note that this is class is not abstract. Maybe it would be better as an abstract class, but for now, I
 * can kinda view a chest or fountain or some random scenery just straight being a GameObject.
 */
public abstract class GameObject
{

	protected int x;
	protected int y;

	protected int xSize;
	protected int ySize;

	protected float rotation;

	protected GraphicComponent gc;
	protected Collider c;

	protected ArrayList<GraphicComponent> labelList;

	public GameObject(int x, int y, int xSize, int ySize,int rotation, String texture, LayerType layer, boolean shouldCollide,
			boolean animated)
	{
		
		this.x = x;
		this.y = y;
		this.xSize = xSize;
		this.ySize = ySize;
		this.rotation = rotation;
		if (animated)
		{
			gc = new AnimationComponent(x, y, xSize, ySize, texture, layer,this);
		}
		else
		{
			gc = new GraphicComponent(x, y, xSize, ySize, texture, layer);
		}
		gc.setRot(rotation);
		c = ColliderHandler.getColliderHandler().addCollider(this, shouldCollide);
		labelList = new ArrayList<GraphicComponent>();
	}
	public GameObject(int x, int y, int xSize, int ySize, String texture, LayerType layer, boolean shouldCollide,
			boolean animated)
	{
		this(x, y, xSize, ySize, 0, texture, layer, shouldCollide, animated);
	}

	public int getX()
	{
		return x;
	}

	public int getY()
	{
		return y;
	}

	public void setPos(int xin, int yin)
	{
		x = xin;
		y = yin;
		gc.setPos(xin, yin);
		c.setPos(xin, yin);
		for (GraphicComponent label : labelList)
		{
			label.setPos(xin, yin);
		}

	}

	public void setRotation(int newRot)
	{
		rotation = newRot;
		gc.setRot(newRot);
	}

	public int getxSize()
	{
		return xSize;
	}

	public void setxSize(int xSize)
	{
		this.xSize = xSize;
	}

	public int getySize()
	{
		return ySize;
	}

	public void setySize(int ySize)
	{
		this.ySize = ySize;
	}

	public float getRotation()
	{
		return rotation;
	}

	public Collider getCollider()
	{
		return c;
	}

	public void turnTo(int xin, int yin)
	{
		int newRot = (int) Functions.angleMeasure(x, y, xin, yin);
		setRotation(newRot);

	}

	public void addLabel(GraphicComponent label)
	{
		labelList.add(label);
	}

	/*
	 * Stub. Maybe gameObject will end up actually using this, but this is
	 * mostly for something like traps inheriting from this class.
	 */
	public void onHit(GameObject gameObject)
	{

	}

	public void update()
	{
		
	}
	
	public void kill()
	{
		gc.kill();
		c.kill();
		for(GraphicComponent graphic : labelList)
		{
			graphic.kill();
		}
	}


}
