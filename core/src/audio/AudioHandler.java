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
			
			/*
			sounds.put("bossMusic1",new Audio("res/audio/gameMusic/Molgera.mp3",true)); 
			
			sounds.put("bossMusic2",  new Audio("res/audio/gameMusic/Vordt.mp3",true)); 
			
			sounds.put("bossMusic3",  new Audio("res/audio/gameMusic/NamelessKing.mp3",true)); 
			
			sounds.put("bossMusic4",  new Audio("res/audio/gameMusic/NamelessKing.mp3",true));
			*/
			
			
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
