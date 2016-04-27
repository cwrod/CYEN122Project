package gui;

import game.MainGame;
import game.MainGame.Level;

public class LevelLoadButton extends Button
{
	public static final String DEFAULT_TEXTURE = "continueButton"; 
	public static final int DEFAULT_SIZE_X = 165;
	public static final int DEFAULT_SIZE_Y = 42;
	
	
	
	
	public Level levelToLoad;
	
	public LevelLoadButton(int x, int y, int width, int height, String texture, Level levelToLoad)
	{
		super(x, y, width,height,texture);
		this.levelToLoad = levelToLoad;
	}
	
	public LevelLoadButton(int x, int y)
	{
		this(x,y,DEFAULT_SIZE_X,DEFAULT_SIZE_Y,DEFAULT_TEXTURE,null);
	}
	public LevelLoadButton(int x, int y,Level l)
	{
		this(x,y,DEFAULT_SIZE_X,DEFAULT_SIZE_Y,DEFAULT_TEXTURE,l);
	}
	
	@Override
	public void buttonPressed()
	{
		if(levelToLoad == null)
			MainGame.getMainGame().incrementLevel();
		else
			MainGame.getMainGame().changeLevel(levelToLoad);
	}
}
