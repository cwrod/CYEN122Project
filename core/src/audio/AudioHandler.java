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
		
		private ArrayList<Audio> music;


		public AudioHandler()
		{
			sounds = new HashMap<String,Audio>();
			loadAudio();
		}

		private void loadAudio()
		{
			sounds.put("bossMusic1",new Audio("res/audio/gameMusic/NamelessKing.mp3",true)); 
			
			
			sounds.put("GameMusic1",new Audio("res/audio/gameMusic/GameMusic1.mp3",true)); 
			sounds.put("GameMusic2",new Audio("res/audio/gameMusic/GameMusic2.mp3",true)); 
			sounds.put("GameMusic3",  new Audio("res/audio/gameMusic/VordtMusic.mp3",true)); 
			sounds.put("GameMusic4",  new Audio("res/audio/gameMusic/NamelessKing.mp3",true)); 
			
			//Music must go behind this line
			music = new ArrayList<Audio>();
			music.addAll(sounds.values());
			setMusicVolume(0.1f);
			//Sound effects go after
			
			sounds.put("growl",new RandomAudio(new String[]{
					"res/audio/soundEffects/growl/growl1.wav",
					"res/audio/soundEffects/growl/growl2.wav",
					"res/audio/soundEffects/growl/growl3.wav"
					}, false, 0.6f));
			
			sounds.put("bossTaunt",new RandomAudio(new String[]{
					"res/audio/soundEffects/bossTaunts/taunt1.wav",
					"res/audio/soundEffects/bossTaunts/taunt2.wav",
					"res/audio/soundEffects/bossTaunts/taunt3.wav",
					}, false, 0.8f));
			
			sounds.put("pain",new RandomAudio(new String[]{
					"res/audio/soundEffects/pain/pain1.wav",
					"res/audio/soundEffects/pain/pain2.wav",
					"res/audio/soundEffects/pain/pain3.wav",
					}, false, 1f));
			
			sounds.put("boltOfGod",  new Audio("res/audio/soundEffects/prayers/boltOfGod.wav",false)); 
			sounds.put("deusVult",  new Audio("res/audio/soundEffects/prayers/deusVult.wav",false)); 
			sounds.put("gloryBe",  new Audio("res/audio/soundEffects/prayers/gloryBe.wav",false)); 
			sounds.put("holyRetribution",  new Audio("res/audio/soundEffects/prayers/holyRetribution.wav",false)); 
			sounds.put("ourFather",  new Audio("res/audio/soundEffects/prayers/ourFather.wav",false)); 
			sounds.put("slowTheUnholy",  new Audio("res/audio/soundEffects/prayers/slowTheUnholy.wav",false)); 
			sounds.put("tueriCorpus",  new Audio("res/audio/soundEffects/prayers/tueriCorpus.wav",false)); 
			sounds.put("wingsOfAngels",  new Audio("res/audio/soundEffects/prayers/wingsOfAngels.wav",false)); 
			
			
			sounds.put("swoosh",  new Audio("res/audio/soundEffects/swoosh/swoosh.wav",false));
			
			
			
			
			
			
		}
		
		
		public void playSoundEffect(String key)
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
		
		public void modMusicVolume(float amountToChange)
		{
			for(Audio a : music)
			{
				a.modVolume(amountToChange);;
			}
		}
		public void setMusicVolume(float volume)
		{
			for(Audio a : music)
			{
				a.setVolume(volume);
			}
		}
}
