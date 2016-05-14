package items;

import java.util.ArrayList;

import gameObjects.Building;
import gameObjects.EnemyObject;
import gameObjects.GameObject;
import gameObjects.PlayerObject;
import gameObjects.Projectile;
import graphics.Canvas.LayerType;
import physics.ColliderHandler;

public class Bow extends OnHand
{

	public static final String ID = "bow";
	public static final String TYPE = "bow";
	public static final String FLAVOR = "The favored weapons of cowards";
	
	public static final int ATTACK_RANGE = 100;
	public static final int DAMAGE = 10;
	public static final int SPEED = 300;
	
	
	public Bow(int x, int y, Building owner)
	{
		super(DAMAGE, ATTACK_RANGE, ID, TYPE, FLAVOR, x, y);
	}
	public Bow()
	{
		super(DAMAGE, ATTACK_RANGE, ID, TYPE, FLAVOR);
	}

	@Override
	public void attack(float angle, float modAtt)
	{
		PlayerObject po = PlayerObject.getPlayerObject();
		int x = po.getX() + (po.getxSize() / 2);
		int y = po.getY() + (po.getySize() / 2);
		new Projectile(x,y,10,10,"arrow",LayerType.PLAYER,SPEED,(int)(DAMAGE*modAtt),(int)angle,po);
		playSound();
	}
}
