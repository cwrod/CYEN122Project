package audio;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public class AudioHandler
{

		private static AudioHandler audioLibrarySingleton;

		public static AudioHandler getAudioLibrary()
		{
			if (audioLibrarySingleton == null)
			{
				audioLibrarySingleton = new AudioHandler();
			}
			return audioLibrarySingleton;
		}

		public static void reset()
		{
			audioLibrarySingleton = new AudioHandler();
		}

		private HashMap<String, Sound> sounds;


		public AudioHandler()
		{
			loadAudio();
		}

		private void loadAudio()
		{
			sounds.put("level1music",Gdx.audio.newSound(Gdx.files.internal("res/music/mysound.mp3")));
			
		}


}
