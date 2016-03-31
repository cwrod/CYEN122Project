package graphics;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

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

	public GraphicComponent(int x, int y, int xSize, int ySize, String key, int layer)
	{
		this.x = x;
		this.y = y;
		this.xSize = xSize;
		this.ySize = ySize;
		this.texture = ImageLibrary.getImageLibrary().find(key);
		Canvas.getCanvas().addToLayer(layer, this);
		rotation = 0;

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

		sb.draw(getTexture(), (float) x - Camera.getCamera().getXShift(), (float) y - Camera.getCamera().getYShift(),
				(float) xSize / 2, (float) ySize / 2, (float) xSize, (float) ySize, 1, 1, (float) rotation);

	}

	public void updateTexture(String key)
	{
		this.texture = ImageLibrary.getImageLibrary().find(key);

	}

}
