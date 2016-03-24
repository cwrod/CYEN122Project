package graphics;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GraphicComponent {
	private int x;
	private int y;
	private int xSize;
	private int ySize;
	private Texture texture;
	
	
	
	public GraphicComponent(int x, int y, int xSize, int ySize, String key, int layer) {
		this.x = x;
		this.y = y;
		this.xSize = xSize;
		this.ySize = ySize;
		this.texture = ImageLibrary.find(key);
		Canvas.getCanvas().addToLayer(layer, this);
		
	}
	
	public void setPos(int xin, int yin)
	{
		x=xin;
		y=yin;
	}
	
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public int getXSize() {
		return xSize;
	}
	public int getYSize() {
		return ySize;
	}
	public Texture getTexture() {
		return texture;
	}
	public void paint(SpriteBatch sb) {

		sb.draw(texture,x-Camera.getCamera().getXShift(),y-Camera.getCamera().getYShift(),xSize,ySize);
		
	}

	public void updateTexture(String key) {
		this.texture = ImageLibrary.find(key);
		
	}
	
}
