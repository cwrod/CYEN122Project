package gui;

import java.util.ArrayList;

import graphics.GraphicComponent;

public abstract class GUIComponent
{

	protected int x;
	protected int y;
	protected int xSize;
	protected int ySize;

	private ArrayList<GraphicComponent> graphicComponents;
	private ArrayList<GUIComponent> guiComponents;
	
	public GUIComponent(int x, int y, int xSize, int ySize)
	{
		this.x = x;
		this.y = y;
		this.xSize = xSize;
		this.ySize = ySize;
		
		graphicComponents = new ArrayList<GraphicComponent>();
		guiComponents = new ArrayList<GUIComponent>();
		
	}

	public int getX()
	{
		return x;
	}

	public int getY()
	{
		return y;
	}

	public int getXSize()
	{
		return xSize;
	}

	public int getYSize()
	{
		return ySize;
	}
	
	protected void addComponentToKill(GraphicComponent gc)
	{
		graphicComponents.add(gc);
	}
	protected void addComponentToKill(GUIComponent gc)
	{
		guiComponents.add(gc);
	}
	public void kill()
	{
		for(GraphicComponent gc : graphicComponents)
		{
			gc.kill();
		}
		for(GUIComponent gc : guiComponents)
		{
			gc.kill();
		}
		graphicComponents.clear();
		guiComponents.clear();
	}
}
