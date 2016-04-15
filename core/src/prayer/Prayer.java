package prayer;

import com.badlogic.gdx.Gdx;

import graphics.GraphicComponent;

public abstract class Prayer
{
	private final float coolDownMax;
	
	private float coolDown;
	private String texture;
	
	public Prayer(float coolDownMax, String texture)
	{
		this.coolDownMax = coolDownMax;
		this.texture = texture;
		coolDown = 0;
	}
	
	
	public void activated()
	{
		if(coolDown <= 0)
		{
			doPrayer();
			coolDown = coolDownMax;
		}
	}
	
	public String getTexture()
	{
		return texture;
	}
	
	protected abstract void doPrayer();
	
	//TODO call from GUIHandler
	public void update()
	{
		if(coolDown>0)
		{
			coolDown -= Gdx.graphics.getDeltaTime();
		}
	}
	
	public float getCoolDownPercent()
	{
		return coolDown/coolDownMax;
	}
	
}
