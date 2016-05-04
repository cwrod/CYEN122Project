package prayer;

import com.badlogic.gdx.Gdx;

import ai.EnemyHandler;
import gameObjects.EnemyObject;
import gameObjects.PlayerObject;
import graphics.Canvas.LayerType;
import toolbox.DeltaTime;
import toolbox.Functions;
import graphics.GraphicComponent;

public class GloryBe extends Prayer
{
	public static final float COOLDOWN = 5.0f;
	public static final float DURATION = 1.0f;
	public static final float MAX_RADIUS = 50.0f;
	public static final float DPS = 20.0f;
	
	public static final String ID = "gloryBe";
	

	public GloryBe()
	{
		super(COOLDOWN,DURATION,ID);
	}

	private GraphicComponent flame; 
	private float radius;
	private float damage;
	private boolean isActive;
	
	@Override
	protected void doPrayer()
	{
		isActive = true;
		flame = new GraphicComponent(0,0,1,1,"flame", LayerType.PLAYER);
		setFlamePos();
	}
	
	@Override
	public void update()
	{
		super.update();
		if(isActive)
		{
			setFlamePos();
			damage+=DeltaTime.getDeltaTime().get()*DPS;
			if(damage>1.0f)
			{
				for(EnemyObject eo : EnemyHandler.getEnemyHandler().getEnemies())
				{
					if(Functions.distance(eo, PlayerObject.getPlayerObject())<radius)
					{
						eo.takeDamage((int)damage);
						damage-=(int)damage;
					}
				}
			}
		}
	}
	
	@Override
	public void prayerEnd()
	{
		flame.kill();
		damage = 0;
		flame = null;
		isActive = false;
	}
	
	private void setFlamePos()
	{
		PlayerObject po = PlayerObject.getPlayerObject();
		radius = MAX_RADIUS * ((DURATION-duration)/DURATION);
		int radInt = (int)radius;
		flame.setPos(po.getX()+(int)((float)po.getxSize()/2.0f) - radInt, po.getY()+(int)((float)po.getySize()/2.0f) - radInt);
		flame.setSize(radInt*2, radInt*2);
	}

	@Override
	public String getID()
	{
		return ID;
	}

}
