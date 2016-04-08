package graphics;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Layer
{

	ArrayList<GraphicComponent> graphicComponents;
	ArrayList<GraphicComponent> killList;

	public Layer()
	{
		graphicComponents = new ArrayList<GraphicComponent>();
		killList = new ArrayList<GraphicComponent>();

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
		graphicComponents.removeAll(killList);
		killList.clear();
	}

	public void addGraphicComponent(GraphicComponent gc)
	{
		graphicComponents.add(gc);
	}

	public void removeGraphicComonent(GraphicComponent gc)
	{
		killList.add(gc);
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
