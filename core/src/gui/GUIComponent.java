package gui;

import graphics.Canvas.LayerType;
import graphics.GraphicComponent;

public class GUIComponent
{
	protected GraphicComponent gc;

	protected int x;
	protected int y;
	protected int xSize;
	protected int ySize;

	public GUIComponent(int x, int y, int xSize, int ySize, String texture)
	{
		gc = new GraphicComponent(x, y, xSize, ySize, texture, LayerType.GUI);
		this.x = x;
		this.y = y;
		this.xSize = xSize;
		this.ySize = ySize;
	}
}
