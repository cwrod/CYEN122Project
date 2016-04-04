package toolbox;

import gameObjects.GameObject;

public class Functions
{

	public static double distance(GameObject go1, GameObject go2)
	{
		return Math.sqrt(Math.pow((go2.getX() + (go2.getxSize() / 2)) - (go1.getX() + (go1.getxSize() / 2)), 2)
				+ Math.pow((go2.getY() + (go2.getySize() / 2)) - (go1.getY() + (go1.getySize() / 2)), 2));
	}

	public static double distance(int tarX, int tarY, int x, int y)
	{
		return Math.sqrt(Math.pow(tarX - x, 2) + Math.pow(tarY - y, 2));
	}

	public static double[] sideRatios(double tarX, double tarY, double x, double y)
	{
		double[] answers = new double[2];
		answers[0] = Math.cos(Math.atan2((tarY - y), (tarX - x)));
		answers[1] = Math.sin(Math.atan2((tarY - y), (tarX - x)));

		return answers;
	}

	public static float angleMeasure(double x1, double y1, double x2, double y2)
	{
		return (float) Math.toDegrees(Math.atan2(y2 - y1, x2 - x1));
	}

	public static float angleMeasure(double dx, double dy)
	{
		return (float) Math.toDegrees(Math.atan2(dy, dx));
	}

}
