package physics;

import java.util.ArrayList;

import gameObjects.GameObject;

public class ColliderHandler
{
	private static ColliderHandler colliderHandlerSingleton;
	
	public static ColliderHandler getColliderHandler()
	{
		if(colliderHandlerSingleton == null)
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
	
	public Collider addCollider(GameObject go)
	{
		Collider c = new Collider(go.getX(), go.getY(), go.getxSize(), go.getySize());
		colliders.add(c);
		return c;
	}
	
	public boolean checkForCollisions(Collider c, int xShift, int yShift)
	{

		
		
		for(Collider potentialCollider : colliders)
		{
			if(!c.equals(potentialCollider))
			{
				if(c.willCollide(potentialCollider,xShift,yShift))
				{
					return true;
				}
			}
		}
		return false;
	}
	
}
