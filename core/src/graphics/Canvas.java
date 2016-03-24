package graphics;
import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Canvas 
{

    private static Canvas canvasSingleton;
    public static Canvas getCanvas()
    {
        if(canvasSingleton == null) {
            canvasSingleton = new Canvas();
        }
        
        return canvasSingleton;
    }

    
    
    public static final int WIDTH = 500; //Changing this variable does not change width
    public static final int HEIGHT = 500; //Changing this variable does not change height
    
    private ArrayList<Layer> layers;
    
    private Canvas()
    {

        layers = new ArrayList<Layer>();
        
        for(int i = 0; i<3; i++){
        	layers.add(new Layer());
        }
       
    }
    
    public void addToLayer(int i, GraphicComponent gc)
    {
    	layers.get(i).addGraphicsComponent(gc);
    }

    public void paint(SpriteBatch sb)
    {


    	for(Layer layer : layers)
    	{
    	layer.paint(sb);	
    	}
        
    }






       



}
