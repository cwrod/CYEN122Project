package gui;

import java.util.HashMap;

import game.MainGame;
import prayer.Prayer;

public class PrayerChooser implements ButtonListener
{
	private int index;
	private HashMap<RadioButton,Prayer> choices;
	
	public PrayerChooser(int index)
	{
		this.index = index;
		choices = new HashMap<RadioButton,Prayer>();
	}
	
	public void put(RadioButton rb, Prayer p )
	{
		choices.put(rb,p);
		
	}

	@Override
	public void onButtonPressed(Button b)
	{
		RadioButton rb = (RadioButton) b;
		MainGame.getMainGame().getGameData().setPrayer(index, choices.get(rb));
		
	}
		
}
