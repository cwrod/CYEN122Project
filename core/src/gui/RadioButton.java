package gui;

import graphics.GraphicComponent;
import graphics.Canvas.LayerType;

public class RadioButton extends Button
{
	private GraphicComponent selected;
	public RadioButton(int x, int y, int xSize, int ySize, String texture)
	{
		super(x, y, xSize, ySize, texture);
		// TODO Auto-generated constructor stub
	}
	public void deselect()
	{
		selected.kill();
		selected = null;
	}

	public void select()
	{
		if(selected == null)
			selected = new GraphicComponent(x, y, xSize, ySize, "selected", LayerType.GUI);
		
	}


}
