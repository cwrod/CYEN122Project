package graphics;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import graphics.Canvas.LayerType;

/*
 * This class describes the actual objects that should be shown on the screen.
 * 
 * If it needs to be rendered, it should probably be of type GraphicComponent.
 */
public class GraphicComponent
{
	protected int x;
	protected int y;
	protected int xSize;
	protected int ySize;
	protected int rotation;
	protected TextureRegion texture;

	protected LayerType layer;

	public GraphicComponent(int x, int y, int xSize, int ySize, String key, LayerType l)
	{
		this.x = x;
		this.y = y;
		this.xSize = xSize;
		this.ySize = ySize;
		this.texture = ImageLibrary.getImageLibrary().find(key);
		if(this.texture == null)
		{
			throw new NullPointerException("No such texture: "+key);
		}
		Canvas.getCanvas().addToLayer(l, this);
		rotation = 0;
		this.layer = l;
	}

	public void setPos(int xin, int yin)
	{
		x = xin;
		y = yin;
	}

	public void setRot(int rotationIn)
	{
		rotation = rotationIn;
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

	public TextureRegion getTexture()
	{
		return texture;
	}

	public void paint(SpriteBatch sb)
	{
		
		if (layer == LayerType.GUI)
		{
			sb.draw(getTexture(), (float) x, (float) y, (float) xSize / 2, (float) ySize / 2, (float) xSize,
					(float) ySize, 1, 1, (float) rotation);
		}
		else
		{
			sb.draw(getTexture(), (float) x - Camera.getCamera().getXShift(),
					(float) y - Camera.getCamera().getYShift(), (float) xSize / 2, (float) ySize / 2, (float) xSize,
					(float) ySize, 1, 1, (float) rotation);
		}
	}
	public void changeLayer(LayerType newLayer)
	{
		Canvas.getCanvas().removeFromLayer(layer, this);
		Canvas.getCanvas().addToLayer(newLayer, this);
	}
	public void updateTexture(String key)
	{
		this.texture = ImageLibrary.getImageLibrary().find(key);
	}

	public boolean isDone(String track)
	{
		return true;
	}

	public void updateSet(String key)
	{

	}

	public void kill()
	{
		Canvas.getCanvas().removeFromLayer(layer, this);
	}

	public void setSize(int xSize, int ySize)
	{
		this.xSize = xSize;
		this.ySize = ySize;
	}

	public String getCurrentTrack()
	{
		return null;
	}
}
