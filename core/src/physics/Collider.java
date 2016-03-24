package physics;

public class Collider
{
	private int x;
	private int y;
	private int xSize;
	private int ySize;

	
	
	public Collider(int x, int y, int xSize, int ySize)
	{
		this.x = x;
		this.y = y;
		this.xSize = xSize;
		this.ySize = ySize;
		
	}

	public boolean willCollide(int xin, int yin)
	{
		if(xin>x&&xin<x+xSize){
			if(yin>y&&yin<y+ySize)
			{
				return true;
			}
		}
		return false;
	}
	public boolean willCollide(Collider c, int xShift, int yShift)
	{
		if(c.getX()+c.getxSize()+xShift>x&&c.getX()+xShift<x+xSize){
			if(c.getY()+c.getySize()+yShift>y&&c.getY()+yShift<y+ySize)
			{
				return true;
			}
		}
		return false;
	}

	public void setPos(int xin, int yin)
	{
		x=xin;
		y=yin;
	}
	
	public int getX()
	{
		return x;
	}

	public int getY()
	{
		return y;
	}

	public int getxSize()
	{
		return xSize;
	}

	public int getySize()
	{
		return ySize;
	}
	
	
	
}
