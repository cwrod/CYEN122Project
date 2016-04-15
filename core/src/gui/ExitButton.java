package gui;

public class ExitButton extends Button
{

	public ExitButton(int x, int y, float scale, String texture)
	{
		super(x, y,(int)(250f*scale),(int)(150f*scale), texture);
	}
	public ExitButton(int x, int y, float scale)
	{
		this(x,y,scale,"exitButton");
	}
	public ExitButton(int x, int y)
	{
		this(x,y,1.0f);
	}
	
	@Override
	public void buttonPressed()
	{
		System.exit(0);
	}

}
