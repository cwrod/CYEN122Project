package gameObjects;

import com.badlogic.gdx.Gdx;

import ai.GameObjectHandler;
import graphics.Canvas.LayerType;
import physics.Collider;
import toolbox.DeltaTime;

public class Projectile extends MobileGameObject
{	
	private double speed;
	private int damage;
	public Projectile(int x, int y, int xSize, int ySize, String texture, LayerType layer, double speed, int damage, int rotation)
	{
		super(x, y, xSize, ySize, rotation, texture, layer, true, false);
		this.speed = speed;
		GameObjectHandler.getEnemyHandler().add(this);
		this.damage = damage;
		shouldRotate = false;
	}
	@Override
	public void update()
	{
		moveForward(speed*DeltaTime.get());
	}
	
	@Override
	public void onHit(GameObject hit)
	{
		if(hit instanceof EnemyObject)
		{
			EnemyObject enemy = (EnemyObject) hit;
			enemy.takeDamage(damage);
		}
		kill();
		GameObjectHandler.getEnemyHandler().remove(this);
		
	}

}
