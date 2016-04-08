package graphics;

import java.util.HashMap;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/*
 * Collection class which loads all our images and animations.
 * 
 */
public class ImageLibrary
{

	private static ImageLibrary imageLibrarySingleton;

	public static ImageLibrary getImageLibrary()
	{
		if (imageLibrarySingleton == null)
		{
			imageLibrarySingleton = new ImageLibrary();
		}
		return imageLibrarySingleton;
	}

	private HashMap<String, TextureRegion> images;
	private HashMap<String, Animation> animations;
	private HashMap<String, HashMap<String, Animation>> animationSet;

	private HashMap<String, Integer> setLengths;

	public ImageLibrary()
	{
		loadImages();
	}

	public void loadImages()
	{

		try
		{
			images = new HashMap<String, TextureRegion>();
			images.put("example", new TextureRegion(new Texture("res/myTex.png"), 0, 0, 1.0f, 1.0f));
			images.put("animError", new TextureRegion(new Texture("res/myTex.png"), 0, 0, 1.0f, 1.0f));
			images.put("person", new TextureRegion(new Texture("res/Person.png"), 0, 0, 1.0f, 1.0f));

			images.put("goblin", new TextureRegion(new Texture("res/goblinStatic.png"), 0, 0, 1.0f, 1.0f));
			images.put("rustySword", new TextureRegion(new Texture("res/rustySword.png"), 0, 0, 1.0f, 1.0f));
			images.put("legendarySword", new TextureRegion(new Texture("res/legendarySword.png"), 0, 0, 1.0f, 1.0f));

			images.put("stone:1",
					new TextureRegion(new Texture("res/buildingGenPatterns/stone/pattern1.png"), 0, 0, 1.0f, 1.0f));

			images.put("healthBar", new TextureRegion(new Texture("res/healthFull.png"), 0, 0, 1.0f, 1.0f));
			images.put("healthLostBar", new TextureRegion(new Texture("res/healthLost.png"), 0, 0, 1.0f, 1.0f));

			setLengths = new HashMap<String, Integer>();
			images.put("level1-1", new TextureRegion(new Texture("res/grass.png"), 0, 0, 1.0f, 1.0f));
			images.put("level1-2", new TextureRegion(new Texture("res/dirt1.png"), 0, 0, 1.0f, 1.0f));
			images.put("level1-3", new TextureRegion(new Texture("res/dirt2.png"), 0, 0, 1.0f, 1.0f));

			setLengths.put("level1", 3);

			animationSet = new HashMap<String, HashMap<String, Animation>>();

			animations = new HashMap<String, Animation>();
			animations.put("default", new Animation("res/rustySwordIdleAnim.png", 50, 10, true));
			animations.put("attacking", new Animation("res/rustySwordAttackAnim.png", 10, 10, false));

			animationSet.put("rustySword", animations);

			animations = new HashMap<String, Animation>();
			animations.put("default", new Animation("res/legendarySwordIdleAnim.png", 50, 20, true));
			animations.put("attacking", new Animation("res/legendarySwordAttackAnim.png", 10, 20, false));

			animationSet.put("legendarySword", animations);

			animations = new HashMap<String, Animation>();
			animations.put("default", new Animation("res/goblinIdleAnim.png", 50, 10, true));
			animations.put("attacking", new Animation("res/goblinAttackAnim.png", 10, 10, false));

			animationSet.put("goblin", animations);

		}
		catch (Exception e)
		{
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

	public int lengthOfSet(String level)
	{
		return setLengths.get(level);
	}
}
