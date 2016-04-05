package physics;

import gameObjects.GameObject;

public class Collider
{
	private int x;
	private int y;
	private int xSize;
	private int ySize;

	private boolean hittable;

	private GameObject owner;

	public Collider(int x, int y, int xSize, int ySize, boolean shouldCollide, GameObject owner)
	{
		this.x = x;
		this.y = y;
		this.xSize = xSize;
		this.ySize = ySize;
		this.hittable = shouldCollide;
		this.owner = owner;
	}

	public Collider(int x, int y, int xSize, int ySize)
	{
		this.x = x;
		this.y = y;
		this.xSize = xSize;
		this.ySize = ySize;
		this.hittable = true;
		this.owner = null;
		ColliderHandler.getColliderHandler().addCollider(this);
	}

	public boolean isHittable()
	{
		return hittable;
	}

	public boolean willCollide(int xin, int yin)
	{

		if (xin >= x && xin <= x + xSize)
		{
			if (yin >= y && yin <= y + ySize)
			{
				return true;
			}
		}
		return false;
	}

	public boolean willCollide(Collider c, int xShift, int yShift)
	{
		if (xShift != 0)
		{
			if (willCollideX(c, xShift))
				return true;
		}
		if (yShift != 0)
		{
			if (willCollideY(c, yShift))
			{
				return true;
			}
		}
		return false;

	}

	public boolean overlaps(Collider c)
	{
		if (c.getX() + c.getxSize() > x && c.getX() < x + xSize)
			if (c.getY() + c.getySize() > y && c.getY() < y + ySize)
				return true;
		return false;

	}

	private boolean willCollideX(Collider c, int xShift)
	{
		if (xShift > 0)
		{
			if (x + xSize + xShift > c.getX() && x + xSize <= c.getX())
			{
				if (c.getY() + c.getySize() > y && c.getY() < y + ySize)
				{
					return true;
				}
			}
			return false;

		}
		else
		{
			if (x + xShift < c.getX() + c.getxSize() && x >= c.getX() + c.getxSize())
			{
				if (c.getY() + c.getySize() > y && c.getY() < y + ySize)
				{
					return true;
				}
			}
			return false;
		}
	}

	private boolean willCollideY(Collider c, int yShift)
	{
		if (yShift > 0)
		{

			if (y + ySize + yShift > c.getY() && y + ySize <= c.getY())
			{
				if (c.getX() + c.getxSize() > x && c.getX() < x + xSize)
				{
					return true;
				}
			}
			return false;

		}
		else
		{
			if (y + yShift < c.getY() + c.getySize() && y >= c.getY() + c.getySize())
			{
				if (c.getX() + c.getxSize() > x && c.getX() < x + xSize)
				{
					return true;
				}
			}
			return false;
		}
	}

	public void setPos(int xin, int yin)
	{
		x = xin;
		y = yin;
	}

	public int getX()
	{
		return x;
	}

	public int getY()
	{
		return y;
	}

	public int getxSize()
	{
		return xSize;
	}

	public int getySize()
	{
		return ySize;
	}

	public GameObject getGameObject()
	{
		return owner;
	}

}
