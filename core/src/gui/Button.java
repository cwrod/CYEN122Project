package gui;

import graphics.GraphicComponent;
import graphics.Canvas.LayerType;

public class Button extends GUIComponent
{
	GraphicComponent buttonGC;
	ButtonListener owner;
	public Button(int x, int y, int xSize, int ySize, String texture)
	{
		super(x, y, xSize, ySize);
		buttonGC = new GraphicComponent(x, y, xSize, ySize, texture, LayerType.GUI);
	}
	
	public void addButtonListener(ButtonListener listener)
	{
		owner = listener;
	}
	
	public void buttonPressed()
	{
		if(owner != null)
			owner.onButtonPressed(this);
	}

}
