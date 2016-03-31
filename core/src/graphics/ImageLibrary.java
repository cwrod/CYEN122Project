package graphics;

import java.util.HashMap;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;


/*
 * Collection class which loads all our images and animations.
 */
public class ImageLibrary {

	private static ImageLibrary imageLibrarySingleton;
	
	public static ImageLibrary getImageLibrary()
	{
		if(imageLibrarySingleton==null)
		{
			imageLibrarySingleton = new ImageLibrary();
		}
		return imageLibrarySingleton;
	}
	
	
	private HashMap<String, TextureRegion> images;
	private HashMap<String, Animation> animations;
	private HashMap<String, HashMap<String,Animation>> animationSet;

	public ImageLibrary()
	{
		loadImages();
	}
	
	public void loadImages()
	{
		
		
		
        try {
	        images = new HashMap<String,TextureRegion>();
			images.put("example",new TextureRegion(new Texture("res/myTex.png"), 0, 0, 1.0f, 1.0f));
			images.put("animError",new TextureRegion(new Texture("res/myTex.png"), 0, 0, 1.0f, 1.0f));
			images.put("person", new TextureRegion(new Texture("res/Person.png"), 0, 0, 1.0f, 1.0f));
			images.put("grass", new TextureRegion(new Texture("res/grass.png"), 0, 0, 1.0f, 1.0f));
			images.put("dirt1", new TextureRegion(new Texture("res/dirt1.png"), 0, 0, 1.0f, 1.0f));
			images.put("dirt2", new TextureRegion(new Texture("res/dirt2.png"), 0, 0, 1.0f, 1.0f));
			images.put("goblin", new TextureRegion(new Texture("res/goblin.png"), 0, 0, 1.0f, 1.0f));
			
			animationSet = new HashMap<String, HashMap<String,Animation>>();
			animations = new HashMap<String,Animation>();
			animations.put("playerWalking", new Animation("res/exampleAnim.png", 50, 10,true));
			animations.put("attacking", new Animation("res/attackAnim.png", 10, 10,false));
			
			animationSet.put("player", animations);
           
           
           
           
        }catch(Exception e){
        	System.out.println(e);
        }
	}

	public TextureRegion find(String key) 
	{
		return images.get(key);
	}
	public HashMap<String, Animation> findAnim(String key) 
	{
		return animationSet.get(key);
	}
}
