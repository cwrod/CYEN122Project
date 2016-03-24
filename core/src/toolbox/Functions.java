package toolbox;

import gameObjects.GameObject;

public class Functions {
	
	public static double distance(GameObject go1, GameObject go2){
		return Math.sqrt(Math.pow(go2.getX()-go1.getX(),2)+Math.pow(go2.getY()-go1.getY(),2));
	}
	
	public static double distance(int tarX, int tarY, int x, int y){
		return Math.sqrt(Math.pow(tarX-x,2)+Math.pow(tarY-y,2));
	}

	public static double[] sideRatios(int tarX, int tarY, int x, int y)
	{
		double [] answers = new double[2];
		answers[0] = Math.cos(Math.atan2((double)(tarY-y),(double)(tarX-x)));
		answers[1] = Math.sin(Math.atan2((double)(tarY-y),(double)(tarX-x)));

		return answers;
	}

}
