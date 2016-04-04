package physics;

import java.util.ArrayList;

import gameObjects.GameObject;

public class ColliderHandler
{
	private static ColliderHandler colliderHandlerSingleton;

	public static ColliderHandler getColliderHandler()
	{
		if (colliderHandlerSingleton == null)
		{
			colliderHandlerSingleton = new ColliderHandler();
		}
		return colliderHandlerSingleton;
	}

	private ArrayList<Collider> colliders;

	public ColliderHandler()
	{
		colliders = new ArrayList<Collider>();
	}

	public void addCollider(Collider c)
	{
		colliders.add(c);
	}

	public Collider addCollider(GameObject go, boolean shouldCollide)
	{
		Collider c = new Collider(go.getX(), go.getY(), go.getxSize(), go.getySize(), shouldCollide, go);
		colliders.add(c);
		return c;
	}

	public Collider willHitObject(Collider c, int xShift, int yShift)
	{

		for (Collider potentialCollider : colliders)
		{
			if (!c.equals(potentialCollider) && potentialCollider.isHittable())
			{
				if (c.willCollide(potentialCollider, xShift, yShift))
				{
					return potentialCollider;
				}
			}
		}
		return null;
	}

	public ArrayList<GameObject> getObjectsOverlapping(Collider c)
	{
		ArrayList<GameObject> goSet = new ArrayList<GameObject>();
		for (Collider potentialCollider : colliders)
		{
			if (!c.equals(potentialCollider) && !potentialCollider.isHittable())
			{
				if (c.overlaps(potentialCollider) && potentialCollider.getGameObject() != null)
				{
					goSet.add(potentialCollider.getGameObject());
				}
			}
		}
		return goSet;
	}

	public ArrayList<GameObject> checkAllCollisons(int x, int y, Class<?> classToCheck)
	{

		ArrayList<GameObject> c = new ArrayList<GameObject>();
		for (Collider potentialCollider : colliders)
		{
			if (potentialCollider.willCollide(x, y) && classToCheck.isInstance(potentialCollider))
			{
				c.add(potentialCollider.getGameObject());
			}
		}
		return c;
	}

}
