package gui;

import game.MainGame;
import gameObjects.PlayerObject;
import graphics.GraphicComponent;
import graphics.Canvas.LayerType;
import prayer.Prayer;

public class PrayerBar extends GUIComponent
{
	private Prayer[] playerPrayers;
	private GraphicComponent[] transparents;
	private int boxSize;
	public PrayerBar(int x, int y, int boxSize)
	{
		super(x, y, boxSize*4, boxSize);
		playerPrayers = MainGame.getMainGame().getGameData().getPrayers();
		transparents = new GraphicComponent[4];
		this.boxSize = boxSize;
		for(int i = 0; i < playerPrayers.length; i++)
		{
			new GraphicComponent(x+(boxSize*i), y, boxSize, boxSize, "nullBox", LayerType.GUI);
			
			if(playerPrayers[i]!=null)
			{
				new GraphicComponent(x+(boxSize*i)+10, y+10, boxSize-20, boxSize-20, playerPrayers[i].getTexture(), LayerType.GUI);	
				transparents[i] = new GraphicComponent(x+(boxSize*i), y, 0, 0, "transparency", LayerType.GUI);	
			}
		}
	}
	
	public void update()
	{
		for(int i = 0; i < playerPrayers.length; i++)
		{
			if(playerPrayers[i]!=null)
			{
				playerPrayers[i].update();
				transparents[i].setSize(boxSize,(int)(boxSize*playerPrayers[i].getCoolDownPercent()));
			}	
		}
	}
	
	public void prayerCalled(int index)
	{
		if(playerPrayers[index]!=null)
		{
			playerPrayers[index].activated();
			PlayerObject.getPlayerObject().pray();
		}
	}

}
