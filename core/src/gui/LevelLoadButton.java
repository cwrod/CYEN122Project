package gui;

import game.MainGame;
import game.MainGame.Level;

public class LevelLoadButton extends Button
{
	public Level levelToLoad;
	
	public LevelLoadButton(int x, int y, float scale, String texture, Level levelToLoad)
	{
		super(x, y, (int)(250f*scale),(int)(150f*scale),texture);
		this.levelToLoad = levelToLoad;
	}
	public LevelLoadButton(int x, int y, float scale,Level levelToLoad)
	{
		this(x, y, scale,"nextLevelButton",levelToLoad);
	}
	public LevelLoadButton(int x, int y, float scale, String texture)
	{
		this(x, y, scale,texture,null);
	}
	public LevelLoadButton(int x, int y, float scale)
	{
		this(x, y, scale,"nextLevelButton");
	}
	public LevelLoadButton(int x, int y)
	{
		this(x, y, 1.0f);
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
