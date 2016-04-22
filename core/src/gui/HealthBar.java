package gui;

import graphics.Canvas.LayerType;
import graphics.GraphicComponent;

public class HealthBar extends GUIComponent
{
	private GraphicComponent health;

	public HealthBar(int x, int y, int xSize, int ySize)
	{
		super(x, y, xSize, ySize);
		new GraphicComponent(x, y, xSize, ySize, "healthLostBar", LayerType.GUI);
		health = new GraphicComponent(x, y, xSize, ySize, "healthBar", LayerType.GUI);

	}

	public void updateHealth(float healthPercent, boolean isPoisoned)
	{
		health.setSize((int) (xSize * healthPercent), ySize);
		if(isPoisoned)
		{
			health.updateTexture("healthPoison");
		}
		else
		{
			health.updateTexture("healthBar");
		}
	}
}
