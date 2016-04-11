package gui;

public abstract class GUIComponent
{

	protected int x;
	protected int y;
	protected int xSize;
	protected int ySize;

	public GUIComponent(int x, int y, int xSize, int ySize)
	{
		this.x = x;
		this.y = y;
		this.xSize = xSize;
		this.ySize = ySize;
	}
}
