package audio;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public class Audio
{
	protected Sound currentSound;
	protected boolean isRepeatable;
	protected float volume;
	public Audio(String source, boolean isRepeatable)
	{
		currentSound = Gdx.audio.newSound(Gdx.files.internal(source));
		this.isRepeatable = isRepeatable;
		this.volume = 1.0f;
	}

	public void play()
	{
		stop();
		if(isRepeatable)
			currentSound.loop(volume);
		else
			currentSound.play(volume);
	}
	public void stop()
	{
		currentSound.stop();
	}
	
	public void setVolume(float volume)
	{
		if(volume<1.0f && volume>0.0f)
			this.volume = volume;
	}
	
	public void modVolume(float amountToChange)
	{
		setVolume(volume+amountToChange);
	}
}
