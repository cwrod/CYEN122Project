package graphics;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import graphics.Canvas.LayerType;

public class TextComponent extends GraphicComponent
{
	public static final int LINE_SIZE = 20;
	private String text;

	public TextComponent(int x, int y, int xSize, int ySize, String text, LayerType l)
	{
		super(x, y, xSize, ySize, "animError", l);
		this.text = text;
	}

	@Override
	public void paint(SpriteBatch sb)
	{
		Canvas.getCanvas().getBitmapFont().draw(sb, text, x, y);
	}

	public void updateText(String newText)
	{
		text = newText;
	}
}
