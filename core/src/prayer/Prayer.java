package prayer;

import com.badlogic.gdx.Gdx;

public abstract class Prayer
{
	private final float coolDownMax;
	
	private float coolDown;
	
	public Prayer(float coolDownMax)
	{
		this.coolDownMax = coolDownMax;
		coolDown = 0;
	}
	
	
	public void activated()
	{
		if(coolDown <= 0)
		{
			doPrayer();
		}
	}
	
	protected abstract void doPrayer();
	
	//TODO call from Player
	public void update()
	{
		if(coolDown>0)
		{
			coolDown -= Gdx.graphics.getDeltaTime();
		}
	}
	
}
