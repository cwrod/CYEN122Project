package audio;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public class Audio
{
	private Sound currentSound;
	private boolean isRepeatable;
	public Audio(String source, boolean isRepeatable)
	{
		currentSound = Gdx.audio.newSound(Gdx.files.internal("res/music/mysound.mp3"));
		this.isRepeatable = isRepeatable;
	}
	public void play()
	{
		stop();
		if(isRepeatable)
			currentSound.loop();
		else
			currentSound.play();
	}
	public void stop()
	{
		currentSound.stop();
	}
	
	
}
