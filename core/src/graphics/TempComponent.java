package graphics;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import graphics.Canvas.LayerType;

public class TempComponent extends GraphicComponent
{

	private int xOffset;
	private int yOffset;

	private int millisecondsToDisplay;

	private long startTime;
	
	private GraphicComponent gc;

	public TempComponent(int x, int y, int xSize, int ySize, int xOffset, int yOffset, float secondsToDisplay,
			String key, LayerType layer)
	{
		super(x, y, xSize, ySize, key, layer);

		this.xOffset = xOffset;
		this.yOffset = yOffset;
		this.millisecondsToDisplay = (int) (secondsToDisplay * 1000);
		startTime = System.currentTimeMillis();
		gc = null;
	}
	public TempComponent(float secondsToDisplay, GraphicComponent gc)
	{
		super(gc.x,gc.y,gc.xSize,gc.ySize,"animError",gc.layer);
		this.gc = gc;
	}
	

	@Override
	public void paint(SpriteBatch sb)
	{
		if (System.currentTimeMillis() - startTime > millisecondsToDisplay)
		{
			kill();
			return;
		}
		if(gc==null)
		{
			sb.draw(getTexture(), (float) x - Camera.getCamera().getXShift() + xOffset,
					(float) y - Camera.getCamera().getYShift() + yOffset, (float) xSize / 2, (float) ySize / 2,
					(float) xSize, (float) ySize, 1, 1, (float) rotation);
		}
		else
		{
			sb.draw(gc.getTexture(), (float) gc.getX() - Camera.getCamera().getXShift(), (float) gc.getY() - Camera.getCamera().getYShift(), gc.getXSize()/2,gc.getYSize()/2,
					gc.getXSize(),gc.getYSize(), 1, 1, (float) rotation);
		}

	}

}
