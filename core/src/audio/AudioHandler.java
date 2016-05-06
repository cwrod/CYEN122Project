package audio;

import java.util.ArrayList;
import java.util.HashMap;

import game.MainGame.Level;

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

		public void softReset(Level l)
		{
			for(String a : sounds.keySet())
			{
				sounds.get(a).stop();
			}
			
			if(l.isPlayable())
			{
				switch(l)
				{
					case FAMINE:
						changeMusic("GameMusic2");
						break;
					case PLAGUE:
						changeMusic("GameMusic1");
						break;
					case WAR:
						changeMusic("GameMusic3");
						break;
					case DEATH:
						changeMusic("GameMusic4");
						break;			
				}
			}
			else
			{
				changeMusic("GameMusic2");
			}
		}

		private HashMap<String, Audio> sounds;
		private Audio backgroundMusic;
		


		public AudioHandler()
		{
			sounds = new HashMap<String,Audio>();
			loadAudio();
		}

		private void loadAudio()
		{
			sounds.put("GameMusic2",new Audio("res/audio/gameMusic/GameMusic2.mp3",true)); 
			sounds.put("bossMusic1",new Audio("res/audio/gameMusic/NamelessKing.mp3",true)); 
			
			
			sounds.put("GameMusic1",new Audio("res/audio/gameMusic/GameMusic1.mp3",true)); 
			
			sounds.put("GameMusic3",  new Audio("res/audio/gameMusic/VordtMusic.mp3",true)); 
			
			sounds.put("GameMusic4",  new Audio("res/audio/gameMusic/NamelessKing.mp3",true)); 
			
			
			
			
			
		}
		
		public void addSoundEffect(String key)
		{
			sounds.get(key).play();
		}
		
		public void changeMusic(String key)
		{
			if(backgroundMusic!=null)
				backgroundMusic.stop();
			backgroundMusic = sounds.get(key);
			backgroundMusic.play();
		}
}
