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

	public static void reset()
	{
		imageLibrarySingleton = new ImageLibrary();
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
			images.put("animError", new TextureRegion(new Texture("res/myTex.png"), 0, 0, 1.0f, 1.0f));
			

			images.put("gameOver", new TextureRegion(new Texture("res/cutscenes/gameOver.png"), 0, 0, 1.0f, 1.0f));
			images.put("gameWon", new TextureRegion(new Texture("res/cutscenes/gameWon.png"), 0, 0, 1.0f, 1.0f));

			images.put("rustySword",
					new TextureRegion(new Texture("res/items/weapons/rustySword.png"), 0, 0, 1.0f, 1.0f));
			images.put("legendarySword",
					new TextureRegion(new Texture("res/items/weapons/legendarySword.png"), 0, 0, 1.0f, 1.0f));

			images.put("holyWater", new TextureRegion(new Texture("res/items/relics/holyWater.png"), 0, 0, 1.0f, 1.0f));

			images.put("doorClosed", new TextureRegion(new Texture("res/items/misc/doorClosed.png"), 0, 0, 1.0f, 1.0f));
			images.put("doorOpen", new TextureRegion(new Texture("res/items/misc/doorOpen.png"), 0, 0, 1.0f, 1.0f));

			images.put("stone:1",
					new TextureRegion(new Texture("res/buildingGenPatterns/stone/pattern1.png"), 0, 0, 1.0f, 1.0f));
			images.put("stone:BOSS",
					new TextureRegion(new Texture("res/buildingGenPatterns/stone/patternBOSS.png"), 0, 0, 1.0f, 1.0f));

			images.put("healthBar", new TextureRegion(new Texture("res/gui/healthFull.png"), 0, 0, 1.0f, 1.0f));
			images.put("healthLostBar", new TextureRegion(new Texture("res/gui/healthLost.png"), 0, 0, 1.0f, 1.0f));
			images.put("inventory", new TextureRegion(new Texture("res/gui/inventory.png"), 0, 0, 1.0f, 1.0f));

			setLengths = new HashMap<String, Integer>();
			images.put("level1-1", new TextureRegion(new Texture("res/tiles/level1/grass.png"), 0, 0, 1.0f, 1.0f));
			images.put("level1-2", new TextureRegion(new Texture("res/tiles/level1/dirt1.png"), 0, 0, 1.0f, 1.0f));
			images.put("level1-3", new TextureRegion(new Texture("res/tiles/level1/dirt2.png"), 0, 0, 1.0f, 1.0f));

			setLengths.put("level1", 3);

			animationSet = new HashMap<String, HashMap<String, Animation>>();

			animations = new HashMap<String, Animation>();
			animations.put("default", new Animation("res/character/rustySword/rustySwordIdleAnim.png", 50, 10, true));
			animations.put("attacking",
					new Animation("res/character/rustySword/rustySwordAttackAnim.png", 10, 10, false));

			animationSet.put("rustySword", animations);

			animations = new HashMap<String, Animation>();
			animations.put("default",
					new Animation("res/character/legendarySword/legendarySwordIdleAnim.png", 50, 20, true));
			animations.put("attacking",
					new Animation("res/character/legendarySword/legendarySwordAttackAnim.png", 10, 20, false));

			animationSet.put("legendarySword", animations);

			animations = new HashMap<String, Animation>();
			animations.put("default", new Animation("res/enemy/goblin/goblinIdleAnim.png", 50, 10, true));
			animations.put("attacking", new Animation("res/enemy/goblin//goblinAttackAnim.png", 10, 10, false));

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
