package prayer;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;

import ai.EnemyHandler;
import gameObjects.EnemyObject;
import gameObjects.GameObject;
import gameObjects.HitListener;
import gameObjects.PlayerObject;
import gameObjects.Projectile;
import graphics.Canvas;
import graphics.Canvas.LayerType;
import graphics.GraphicComponent;
import graphics.TempComponent;
import toolbox.DeltaTime;
import toolbox.Functions;

public class BoltOfGod extends Prayer
{
	public static final float COOLDOWN = 10.0f;
	public static final String ID = "boltOfGod";
	
	public static final int SPEED = 300;
	public static final int DAMAGE = 50;
	
	public static final float RADIUS = 100;
	
	
	private float radius=RADIUS;
	private GraphicComponent explosion;
	private ArrayList<GameObject> hitEnemies;
	private int explosionX;
	private int explosionY;
	
	public BoltOfGod()
	{
		super(COOLDOWN, ID);
	}

	@Override
	protected void doPrayer()
	{
		PlayerObject po = PlayerObject.getPlayerObject();
		int x = po.getX() + (po.getxSize() / 2);
		int y = po.getY() + (po.getySize() / 2);
		Projectile p = new Projectile(x,y,10,10,"arrow",LayerType.PLAYER,SPEED,DAMAGE,
				(int)Functions.angleMeasure(((float)Canvas.WIDTH)/2.0f, ((float)Canvas.HEIGHT)/2.0f, Gdx.input.getX(),Canvas.HEIGHT-Gdx.input.getY()));
		p.setHitListener(new HitListener()
				{
					@Override
					public void onHit(int x, int y)
					{
						radius = 0;
						explosion = new GraphicComponent(x,y,0,0,"flame", LayerType.PLAYER);
						explosionX = x;
						explosionY = y;
						hitEnemies = new ArrayList<GameObject>();
					}
			
				}
			);
	}
	
	
	@Override
	public void update()
	{
		super.update();
		if(radius<RADIUS)
		{
			radius+=DeltaTime.getDeltaTime().get()*100;
			explosion.setPos(explosionX-(int)radius, explosionY-(int)radius);
			explosion.setSize(2*(int)radius, 2*(int)radius);
			for(EnemyObject eo : EnemyHandler.getEnemyHandler().getEnemies())
			{
				if(!hitEnemies.contains(eo))
				{
					if(Functions.distance(explosionX,explosionY,eo.getX(),eo.getY())<radius)
					{
						eo.takeDamage(DAMAGE);
						hitEnemies.add(eo);
					}
				}
			}
			if(!hitEnemies.contains(PlayerObject.getPlayerObject()))
			{
				if(Functions.distance(explosionX,explosionY,PlayerObject.getPlayerObject().getX(),PlayerObject.getPlayerObject().getY())<radius)
				{
					PlayerObject.getPlayerObject().takeDamage(DAMAGE,null);
					hitEnemies.add(PlayerObject.getPlayerObject());
				}
			}
			if(radius>=RADIUS)
			{
				explosion.kill();
				explosion = null;
			}
		}
	}

	@Override
	public String getID()
	{
		return ID;
	}
	
}
