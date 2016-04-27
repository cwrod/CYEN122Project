package prayer;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;

import toolbox.DeltaTime;

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
			coolDown -= DeltaTime.get();
		}
		if(duration > 0)
		{
			duration -= DeltaTime.get();
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
	public abstract String getID();
	
	
	private static HashMap<String,Class<?>> prayerList;
	
	private static void initPrayerList()
	{
		prayerList = new HashMap<String,Class<?>>();
		
		prayerList.put("gloryBe", GloryBe.class);
		prayerList.put("ourFather", OurFather.class);
		
	}
	
	public static Prayer getPrayerInstance(String key)
	{
		if(prayerList == null)
			initPrayerList();
		try
		{
			return (Prayer) prayerList.get(key).getDeclaredConstructor().newInstance();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
}
