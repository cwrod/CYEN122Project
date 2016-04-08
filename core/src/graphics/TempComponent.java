package graphics;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import graphics.Canvas.LayerType;

public class TempComponent extends GraphicComponent
{

	private int xOffset;
	private int yOffset;

	private int millisecondsToDisplay;

	private long startTime;

	public TempComponent(int x, int y, int xSize, int ySize, int xOffset, int yOffset, float secondsToDisplay,
			String key, LayerType layer)
	{
		super(x, y, xSize, ySize, key, layer);

		this.xOffset = xOffset;
		this.yOffset = yOffset;
		this.millisecondsToDisplay = (int) (secondsToDisplay * 1000);
		startTime = System.currentTimeMillis();
	}

	@Override
	public void paint(SpriteBatch sb)
	{
		if (System.currentTimeMillis() - startTime > millisecondsToDisplay)
		{
			kill();
			return;
		}

		sb.draw(getTexture(), (float) x - Camera.getCamera().getXShift() + xOffset,
				(float) y - Camera.getCamera().getYShift() + yOffset, (float) xSize / 2, (float) ySize / 2,
				(float) xSize, (float) ySize, 1, 1, (float) rotation);

	}

}
