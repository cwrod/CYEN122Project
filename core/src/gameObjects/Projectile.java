package gameObjects;

import com.badlogic.gdx.Gdx;

import ai.GameObjectHandler;
import graphics.Canvas.LayerType;
import physics.Collider;
import physics.ColliderHandler;
import toolbox.DeltaTime;
import toolbox.Functions;

public class Projectile extends MobileGameObject
{	
	private double speed;
	private int damage;
	
	private HitListener hl;
	private Object source;
	public Projectile(int x, int y, int xSize, int ySize, String texture, LayerType layer, double speed, int damage, int rotation, Object source)
	{
		super(x, y, xSize, ySize, rotation, texture, layer, true, false);
		this.speed = speed;
		GameObjectHandler.getEnemyHandler().add(this);
		this.damage = damage;
		shouldRotate = false;
		
		this.source = source;
	}
	
	public void setHitListener(HitListener hl)
	{
		this.hl = hl;
	}
	
	
	private double movementY = 0;
	private double movementX = 0;
	@Override
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
		if (hit == null || hit.getGameObject() instanceof Projectile || ((source instanceof EnemyObject && hit.getGameObject() instanceof EnemyObject) || (source instanceof PlayerObject && hit.getGameObject() instanceof PlayerObject)))
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
		if (hit2 == null || hit2.getGameObject() instanceof Projectile ||  ((source instanceof EnemyObject && hit2.getGameObject() instanceof EnemyObject) || (source instanceof PlayerObject && hit2.getGameObject() instanceof PlayerObject)))
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

	
	@Override
	public void update()
	{
		moveForward(speed*DeltaTime.getDeltaTime().get());
	}
	
	@Override
	public void onHit(GameObject hit)
	{
		if(!(source instanceof EnemyObject && hit instanceof EnemyObject) && !(source instanceof PlayerObject && hit instanceof PlayerObject))
		{
			if(hit instanceof EnemyObject)
			{
				EnemyObject enemy = (EnemyObject) hit;
				enemy.takeDamage(damage);
			}
			else if(hit instanceof PlayerObject)
			{
				if(source instanceof EnemyObject)
					PlayerObject.getPlayerObject().takeDamage(damage,(EnemyObject)source);
				else
					PlayerObject.getPlayerObject().takeDamage(damage,null);
				
			}
			kill();
			GameObjectHandler.getEnemyHandler().remove(this);
			if(hl!=null)
				hl.onHit(x,y);
		}
		
	}

}
