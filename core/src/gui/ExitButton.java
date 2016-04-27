package gui;

import game.MainGame.Level;

public class ExitButton extends Button
{
	public static final String DEFAULT_TEXTURE = "quitButton"; 
	public static final int DEFAULT_SIZE_X = 65;
	public static final int DEFAULT_SIZE_Y = 33;
	
	
	public ExitButton(int x, int y, int width, int height, String texture)
	{
		super(x, y, width,height,texture);
	}
	
	public ExitButton(int x, int y)
	{
		this(x,y,DEFAULT_SIZE_X,DEFAULT_SIZE_Y,DEFAULT_TEXTURE);
	}
	public ExitButton(int x, int y,Level l)
	{
		this(x,y,DEFAULT_SIZE_X,DEFAULT_SIZE_Y,DEFAULT_TEXTURE);
	}
	
	@Override
	public void buttonPressed()
	{
		System.exit(0);
	}

}
