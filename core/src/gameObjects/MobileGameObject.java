package gameObjects;

import graphics.Canvas.LayerType;
import physics.Collider;
import physics.ColliderHandler;
import toolbox.Functions;

/*
 * GameObjects that can move and hit each other
 */
public abstract class MobileGameObject extends GameObject
{

	private double movementX;
	private double movementY;

	protected boolean shouldRotate;

	public MobileGameObject(int xin, int yin, int xSize, int ySize, String texture, LayerType layer,
			boolean shouldRotate, boolean animated)
	{
		this(xin, yin, xSize, ySize, 0, texture, layer, shouldRotate, animated);

	}

	public MobileGameObject(int xin, int yin, int xSize, int ySize, int rotation, String texture, LayerType layer,
			boolean shouldRotate, boolean animated)
	{
		super(xin, yin, xSize, ySize, rotation, texture, layer, true, animated);

		movementX = movementY = 0;
		this.shouldRotate = shouldRotate;
		// TODO Auto-generated constructor stub
	}

	/*
	 * Moves the gameObject unless there is an object that it is about to
	 * collide with.
	 */
	public void move(double dx, double dy)
	{

		if (shouldRotate)
		{
			setRotation((int) Functions.angleMeasure(dx, dy));
		}

		int changeX = 0;
		if (dx * movementX < 0)
		{
			movementX = 0;
		}
		movementX += dx;
		if (movementX > 1.0 || movementX < -1.0)
		{
			changeX += movementX - (movementX % 1);

			movementX -= movementX - (movementX % 1);

		}
		Collider hit = ColliderHandler.getColliderHandler().willHitObject(c, changeX, 0);
		if (hit == null)
		{
			setPos(x + changeX, y);
		}
		else
		{
			movementX = 0;
			onHit(hit.getGameObject());
			if (hit.getGameObject() != null)
			{
				hit.getGameObject().onHit(this);
			}
		}

		int changeY = 0;
		if (dy * movementY < 0)
		{
			movementY = 0;
		}
		movementY += dy;
		if (movementY > 1.0 || movementY < -1.0)
		{
			changeY += movementY - (movementY % 1);
			movementY -= movementY - (movementY % 1);
		}
		Collider hit2 = ColliderHandler.getColliderHandler().willHitObject(c, 0, changeY);
		if (hit2 == null)
		{
			setPos(x, y + changeY);
		}
		else
		{
			movementY = 0;
			onHit(hit2.getGameObject());
			if (hit2.getGameObject() != null)
			{
				hit2.getGameObject().onHit(this);
			}
		}
	}

	/*
	 * A simple moveToPoint script that will be pretty useful for most mobile
	 * game objects.
	 */
	public void moveToPoint(double tarX, double tarY, double magnitude)
	{
		double[] sideRatios = Functions.sideRatios(tarX, tarY, x, y);

		move(magnitude * sideRatios[0], magnitude * sideRatios[1]);
	}
	public void moveForward(double magnitude)
	{
		
		move(Math.cos(Math.toRadians(rotation))*magnitude,Math.sin(Math.toRadians(rotation))*magnitude);
	}
}
