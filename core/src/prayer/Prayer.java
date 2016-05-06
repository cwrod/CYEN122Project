package prayer;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;

import audio.AudioHandler;
import toolbox.DeltaTime;

public abstract class Prayer
{
	private final float coolDownMax;
	protected final float durationMax;
	
	private float coolDown;
	protected float duration;
	
	private String ID;
	
	public Prayer(float coolDownMax, float durationMax, String ID)
	{
		this.coolDownMax = coolDownMax;
		this.durationMax = durationMax;
		this.ID = ID;
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
			AudioHandler.getAudioLibrary().playSoundEffect(ID);
			doPrayer();
			coolDown = coolDownMax;
			duration = durationMax;
		}
	}
	
	public String getTexture()
	{
		return ID;
	}
	
	protected abstract void doPrayer();
	
	protected void prayerEnd()
	{
		
	}
	
	
	public void update()
	{
		if(coolDown>0)
		{
			coolDown -= DeltaTime.getDeltaTime().get();
		}
		if(duration > 0)
		{
			duration -= DeltaTime.getDeltaTime().get();
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
		
		prayerList.put(GloryBe.ID, GloryBe.class);
		prayerList.put(OurFather.ID, OurFather.class);
		prayerList.put(BoltOfGod.ID, BoltOfGod.class);
		prayerList.put(DeusVult.ID, DeusVult.class);
		prayerList.put(HolyRetribution.ID, HolyRetribution.class);
		prayerList.put(SlowTheUnholy.ID, SlowTheUnholy.class);
		prayerList.put(TueriCorpus.ID, TueriCorpus.class);
		prayerList.put(WingsOfAngels.ID, WingsOfAngels.class);
		
		
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
