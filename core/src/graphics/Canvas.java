package graphics;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;


/*
 * The main handler for drawing. Delegates actual drawing to layers.
 */
public class Canvas
{
	
	//Basic header for a singleton

	private static Canvas canvasSingleton;

	public static Canvas getCanvas()
	{
		if (canvasSingleton == null)
		{
			canvasSingleton = new Canvas();
		}

		return canvasSingleton;
	}

	public static final int WIDTH = 500; // Changing this variable does not change width
	public static final int HEIGHT = 500; // Changing this variable does not change height

	private ArrayList<Layer> layers;

	private Canvas()
	{

		layers = new ArrayList<Layer>();

		for (int i = 0; i < 3; i++)
		{
			layers.add(new Layer());
		}

	}
	/*
	 * Adds the GraphicComponent to the specified layer so it can be rendered
	 */
	public void addToLayer(int i, GraphicComponent gc)
	{
		layers.get(i).addGraphicsComponent(gc);
	}

	
	/*
	 * Called by main to paint the screen
	 */
	public void paint(SpriteBatch sb)
	{

		for (Layer layer : layers)
		{
			layer.paint(sb);
		}

	}

}
