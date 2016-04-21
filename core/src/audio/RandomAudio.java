package audio;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public class RandomAudio extends Audio
{

	Sound[] audioChoices;
	public RandomAudio(String[] source, boolean isRepeatable)
	{
		super(source[0], isRepeatable);
		audioChoices = new Sound[source.length];
		for(int i = 0; i < audioChoices.length; i++)
		{
			audioChoices[i] =  Gdx.audio.newSound(Gdx.files.internal(source[i]));
		}
	}
	
	@Override
	public void play()
	{
		currentSound = audioChoices[new Random().nextInt(audioChoices.length)];
		super.play();
	}
}
