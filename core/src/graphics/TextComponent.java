package graphics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.utils.Align;

import graphics.Canvas.LayerType;

public class TextComponent extends GraphicComponent
{
	public static final int LINE_SIZE = 20;
	private String text;
	private BitmapFont font;
	
	private Color color;
	
	public TextComponent(int x, int y, int xSize, int ySize, String text, LayerType l,Color color)
	{
		super(x, y, xSize, ySize, "animError", l);
		this.text = text;
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("res/fonts/DroidSans.ttf"));
		FreeTypeFontParameter parameter = new FreeTypeFontParameter();
		parameter.size = 14;
		if(color == null)
			parameter.color=Color.BLACK;
		else
			parameter.color = color;
		font = generator.generateFont(parameter); // font size 12 pixels
		generator.dispose();
	}
	public TextComponent(int x, int y, int xSize, int ySize, String text, LayerType l)
	{
		this(x, y, xSize, ySize, text, l,null);;
	}

	@Override
	public void paint(SpriteBatch sb)
	{
		font.draw(sb, text, x, y, xSize, Align.center, true);
	}

	public void updateText(String newText)
	{
		text = newText;
	}
}
