package items;

import java.util.ArrayList;

import gameObjects.EnemyObject;
import gameObjects.GameObject;
import gameObjects.PhysicalItem;
import gameObjects.PlayerObject;
import physics.ColliderHandler;

public abstract class OnHand extends Item
{
	protected int damage;
	protected float attackRange;

	public OnHand(int damage, float attackRange, String name,String flavorText, int x, int y)
	{		
		super(x,y,name,flavorText);
		this.damage = damage;
		this.attackRange = attackRange;

	}

	public OnHand(int damage, float attackRange, String name, String flavorText)
	{
		super(name,flavorText);
		this.damage = damage;
		this.attackRange = attackRange;
	}

	public void attack(float angle)
	{

		PlayerObject po = PlayerObject.getPlayerObject();
		int attackPointX = po.getX() + (po.getxSize() / 2) + (int) (Math.cos(Math.toRadians(angle)) * attackRange);
		int attackPointY = po.getY() + (po.getySize() / 2) + (int) (Math.sin(Math.toRadians(angle)) * attackRange);

		ArrayList<GameObject> eo = ColliderHandler.getColliderHandler().checkAllCollisons(attackPointX, attackPointY,
				EnemyObject.class);
		if (eo.size() > 0)
			((EnemyObject) eo.get(0)).takeDamage(damage);

	}

}
