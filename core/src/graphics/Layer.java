package graphics;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Layer
{

	ArrayList<GraphicComponent> graphicComponents;

	public Layer()
	{
		graphicComponents = new ArrayList<GraphicComponent>();
	}

	public void paint(SpriteBatch sb)
	{
		for (GraphicComponent gc : graphicComponents)
		{
			if (shouldPaint(gc))
			{
				gc.paint(sb);
			}
		}
	}

	public void addGraphicsComponent(GraphicComponent gc)
	{
		graphicComponents.add(gc);
	}

	private boolean shouldPaint(GraphicComponent gc)
	{
		if (gc.getX() + gc.getXSize() < 0 && gc.getX() > Canvas.WIDTH)
		{
			if (gc.getY() + gc.getYSize() > Canvas.HEIGHT && gc.getY() < 0)
			{
				return false;
			}
		}
		return true;

	}
}
