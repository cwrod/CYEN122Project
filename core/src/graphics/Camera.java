package graphics;


public class Camera {
	private int x;
	private int y;
	private int leftScreen;
	private int topScreen;
	
	private static Camera cameraSingleton;
	
	public static Camera getCamera()
	{
		if(cameraSingleton==null)
		{
			cameraSingleton = new Camera();
		}
		return cameraSingleton;
	}
	public Camera(){
		leftScreen = Canvas.WIDTH/2;
		topScreen = Canvas.HEIGHT/2;
	}
	
	public int getXShift(){
		return x-leftScreen;
	}
	public int getYShift(){
		return y-topScreen;
	}
	public void setPos(int xin, int yin)
	{
		x=xin;
		y=yin;
	}
	public void moveX(int xin)
	{
		x+=xin;
	}
	public void moveY(int yin)
	{
		y+=yin;
	}

	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	
}
