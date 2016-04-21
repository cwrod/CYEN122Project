package audio;

import java.util.ArrayList;
import java.util.HashMap;

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

		private HashMap<String, Audio> sounds;
		private ArrayList<Audio> currentSoundEffects;
		private Audio backgroundMusic;
		


		public AudioHandler()
		{
			loadAudio();
		}

		private void loadAudio()
		{
			sounds.put("level1music",new Audio("res/music/mysound.mp3",true));
			
		}
		public void changeMusic(String key)
		{
			backgroundMusic.stop();
			backgroundMusic = sounds.get(key);
			backgroundMusic.play();
		}
}
