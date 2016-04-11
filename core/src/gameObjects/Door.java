package gameObjects;

import graphics.Canvas.LayerType;

public abstract class Door extends GameObject
{
	public Door(int x, int y)
	{
		super(x, y, 50, 50, "doorClosed", LayerType.BUILDINGS, true, false);

	}

	public void setUnlocked()
	{
		c.setHittable(false);
		gc.updateTexture("doorOpen");
	}

	public void setLocked()
	{
		c.setHittable(true);
		gc.updateTexture("doorClosed");
	}

}
