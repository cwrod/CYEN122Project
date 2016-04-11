package graphics;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/*
 * The main handler for drawing. Delegates actual drawing to layers.
 */
public class Canvas
{

	// Basic header for a singleton

	private static Canvas canvasSingleton;

	public static Canvas getCanvas()
	{
		if (canvasSingleton == null)
		{
			canvasSingleton = new Canvas();
		}

		return canvasSingleton;
	}
	public static void reset()
	{
		canvasSingleton = new Canvas();	
	}

	public static final int WIDTH = 500; // Changing this variable does not
											// change width
	public static final int HEIGHT = 500; // Changing this variable does not
											// change height

	public enum LayerType
	{
		BACKGROUND, BUILDINGS, ITEMS, ENEMIES, EFFECTS, PLAYER, GUI
	}

	private ArrayList<Layer> layers;
	private BitmapFont f;

	private Canvas()
	{

		layers = new ArrayList<Layer>();

		for (int i = 0; i < LayerType.values().length; i++)
		{
			layers.add(new Layer());
		}
		f = new BitmapFont();
		f.getData().setScale(1f);
	}

	/*
	 * Adds the GraphicComponent to the specified layer so it can be rendered
	 */
	public void addToLayer(LayerType l, GraphicComponent gc)
	{
		layers.get(l.ordinal()).addGraphicComponent(gc);
	}

	public void removeFromLayer(LayerType l, GraphicComponent gc)
	{
		layers.get(l.ordinal()).removeGraphicComonent(gc);

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

	public BitmapFont getBitmapFont()
	{
		return f;
	}



}
