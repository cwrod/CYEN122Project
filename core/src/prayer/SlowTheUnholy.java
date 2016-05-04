package prayer;

import toolbox.DeltaTime;

public class SlowTheUnholy extends Prayer
{
	public static final float COOLDOWN = 20.0f;
	public static final float DURATION = 5.0f;
	public static final float TIME_FACTOR = 5f;
	public static final String ID = "slowTheUnholy";
	
	public SlowTheUnholy()
	{
		super(COOLDOWN, DURATION, ID);
	}

	@Override
	protected void doPrayer()
	{
		DeltaTime.getDeltaTime().modTime(1.0f/TIME_FACTOR);
	}

	@Override
	public String getID()
	{
		return ID;
	}
	
	@Override
	protected void prayerEnd()
	{
		DeltaTime.getDeltaTime().modTime(TIME_FACTOR);	
	}

}
