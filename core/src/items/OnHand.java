package items;

import java.util.ArrayList;

import ai.EnemyHandler;
import gameObjects.EnemyObject;
import gameObjects.GameObject;
import gameObjects.PhysicalItem;
import gameObjects.PlayerObject;
import graphics.Layer;
import physics.ColliderHandler;

public abstract class OnHand
{
	protected int damage;
	protected float attackRange;
	private String animName;

	public OnHand(int damage, float attackRange, String animName, int x, int y)
	{
		this.damage = damage;
		this.attackRange = attackRange;
		this.animName = animName;
		new PhysicalItem(x, y, animName, this);
	}

	public OnHand(int damage, float attackRange, String animName)
	{
		this.damage = damage;
		this.attackRange = attackRange;
		this.animName = animName;
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

	public String getAnimName()
	{
		return animName;
	}

}
