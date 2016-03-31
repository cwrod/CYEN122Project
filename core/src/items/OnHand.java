package items;

import java.util.ArrayList;

import ai.EnemyHandler;
import gameObjects.EnemyObject;
import gameObjects.GameObject;
import gameObjects.PlayerObject;
import physics.ColliderHandler;

public abstract class OnHand {
	protected int damage;
	
	protected float attackSpeed;
	protected float attackRange;
	
	public OnHand(int damage, float attackSpeed, float attackRange) 
	{
		this.damage = damage;
		this.attackSpeed = attackSpeed;
		this.attackRange = attackRange;
	}


	public void attack(float angle) {

			PlayerObject po = PlayerObject.getPlayerObject();
			int attackPointX = po.getX()+(po.getxSize()/2)+(int)(Math.cos(Math.toRadians(angle))*attackRange);
			int attackPointY = po.getY()+(po.getySize()/2)+(int)(Math.sin(Math.toRadians(angle))*attackRange);
			
			
			ArrayList<GameObject> eo = ColliderHandler.getColliderHandler().checkAllCollisons(attackPointX, attackPointY, EnemyHandler.class);
			if(eo.size()>0)
				((EnemyObject) eo.get(0)).takeDamage(damage);
		
		
	}


	public double getAttackSpeed() {
		return attackSpeed;
	}
	
	
	
}
