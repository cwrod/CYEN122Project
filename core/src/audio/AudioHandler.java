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
			for(String a : audioLibrarySingleton.sounds.keySet())
			{
				audioLibrarySingleton.sounds.get(a).stop();
			}
			audioLibrarySingleton = new AudioHandler();
		}

		private HashMap<String, Audio> sounds;
		private Audio backgroundMusic;
		


		public AudioHandler()
		{
			loadAudio();
			sounds = new HashMap<String,Audio>();
		}

		private void loadAudio()
		{
			sounds.put("level1music",new Audio("res/music/mysound.mp3",true));
		}
		public void addSoundEffect(String key)
		{
			sounds.get(key).play();
		}
		
		public void changeMusic(String key)
		{
			backgroundMusic.stop();
			backgroundMusic = sounds.get(key);
			backgroundMusic.play();
		}
}
