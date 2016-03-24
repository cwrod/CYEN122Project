package graphics;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;

import javax.imageio.ImageIO;

import com.badlogic.gdx.graphics.Texture;

public class ImageLibrary {
	private static HashMap<String,Texture> images;
	
	public static void loadImages()
	{
		images = new HashMap<String,Texture>();
        try {
           images.put("example",new Texture("res/myTex.png"));
           images.put("person", new Texture("res/Person.png"));
           images.put("grass", new Texture("res/grass.png"));
           images.put("dirt1", new Texture("res/dirt1.png"));
           images.put("dirt2", new Texture("res/dirt2.png"));
           images.put("goblin", new Texture("res/goblin.png"));
           
           
           
           
           
           
        }catch(Exception e){
        	System.out.println(e);
        }
	}

	public static Texture find(String key) {
		return images.get(key);
	}

}
