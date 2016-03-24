package map;

import graphics.GraphicComponent;

public abstract class Tile {
	private int tileSize;
	private int x;
	private int y;
	
	protected GraphicComponent gc;


	public Tile(int tileSize)
	{
		this.tileSize = tileSize;
		this.x = 0;
		this.y = 0;
		this.gc = new GraphicComponent(x, y, tileSize, tileSize, randomStyle(), 0);
	}
	
	public abstract String randomStyle();
	
	
	
	public void setPos(int xin, int yin)
	{
		x = xin;
		y = yin;
		gc.setPos(xin, yin);
	}
	
	

}
