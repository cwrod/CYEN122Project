package prayer;

import com.badlogic.gdx.Gdx;

import graphics.GraphicComponent;

public abstract class Prayer
{
	private final float coolDownMax;
	protected final float durationMax;
	
	private float coolDown;
	protected float duration;
	
	private String texture;
	
	
	public Prayer(float coolDownMax, float durationMax, String texture)
	{
		this.coolDownMax = coolDownMax;
		this.durationMax = durationMax;
		this.texture = texture;
		coolDown = 0;
	}
	public Prayer(float coolDownMax, String texture)
	{
		this(coolDownMax,0.0f,texture);
	}
	
	
	public void activated()
	{
		if(coolDown <= 0)
		{
			doPrayer();
			coolDown = coolDownMax;
			duration = durationMax;
		}
	}
	
	public String getTexture()
	{
		return texture;
	}
	
	protected abstract void doPrayer();
	
	protected void prayerEnd()
	{
		
	}
	
	
	public void update()
	{
		if(coolDown>0)
		{
			coolDown -= Gdx.graphics.getDeltaTime();
		}
		if(duration > 0)
		{
			duration -= Gdx.graphics.getDeltaTime();
			if(duration <= 0)
			{
				prayerEnd();
			}
		}
		
	}
	
	
	
	public float getCoolDownPercent()
	{
		return coolDown/coolDownMax;
	}
	
}
