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
			images.put("stone:2",
					new TextureRegion(new Texture("res/buildingGenPatterns/stone/pattern2.png"), 0, 0, 1.0f, 1.0f));
			images.put("stone:BOSS",
					new TextureRegion(new Texture("res/buildingGenPatterns/stone/patternBOSS.png"), 0, 0, 1.0f, 1.0f));

			images.put("healthBar", new TextureRegion(new Texture("res/gui/healthFull.png"), 0, 0, 1.0f, 1.0f));
			images.put("healthLostBar", new TextureRegion(new Texture("res/gui/healthLost.png"), 0, 0, 1.0f, 1.0f));
			images.put("inventory", new TextureRegion(new Texture("res/gui/inventory.png"), 0, 0, 1.0f, 1.0f));
			images.put("compass", new TextureRegion(new Texture("res/gui/compass.png"), 0, 0, 1.0f, 1.0f));
			images.put("exitButton", new TextureRegion(new Texture("res/gui/exit.png"), 0, 0, 1.0f, 1.0f));
			images.put("nextLevelButton", new TextureRegion(new Texture("res/gui/nextLevel.png"), 0, 0, 1.0f, 1.0f));
			images.put("selected", new TextureRegion(new Texture("res/gui/selected.png"), 0, 0, 1.0f, 1.0f));
			images.put("transparency", new TextureRegion(new Texture("res/gui/transparency.png"), 0, 0, 1.0f, 1.0f));
			
			
			images.put("ourFatherChoice", new TextureRegion(new Texture("res/gui/prayers/choices/ourFather.png"), 0, 0, 1.0f, 1.0f));
			images.put("gloryBeChoice", new TextureRegion(new Texture("res/gui/prayers/choices/gloryBe.png"), 0, 0, 1.0f, 1.0f));
			
			
			images.put("ourFatherIcon", new TextureRegion(new Texture("res/gui/prayers/icons/ourFather.png"), 0, 0, 1.0f, 1.0f));
			images.put("gloryBeIcon", new TextureRegion(new Texture("res/gui/prayers/icons/gloryBe.png"), 0, 0, 1.0f, 1.0f));
			images.put("nullBox", new TextureRegion(new Texture("res/gui/prayers/icons/nullBox.png"), 0, 0, 1.0f, 1.0f));
			
			images.put("flame", new TextureRegion(new Texture("res/effects/flame.png"), 0, 0, 1.0f, 1.0f));
			
			
			
			
			

			setLengths = new HashMap<String, Integer>();
			images.put("level1-1", new TextureRegion(new Texture("res/tiles/level1/grass.png"), 0, 0, 1.0f, 1.0f));
			images.put("level1-2", new TextureRegion(new Texture("res/tiles/level1/dirt1.png"), 0, 0, 1.0f, 1.0f));
			images.put("level1-3", new TextureRegion(new Texture("res/tiles/level1/dirt2.png"), 0, 0, 1.0f, 1.0f));

			setLengths.put("level1", 3);
			
			images.put("level2-1", new TextureRegion(new Texture("res/tiles/level2/lava.png"), 0, 0, 1.0f, 1.0f));

			setLengths.put("level2", 1);

			animationSet = new HashMap<String, HashMap<String, Animation>>();

			animations = new HashMap<String, Animation>();
			animations.put("default", new Animation("res/character/sword/swordIdleAnim.png", 24, 34, true));
			animations.put("attacking",
					new Animation("res/character/sword/swordAttackAnim.png", 24, 17, false));
			animations.put("walking", new Animation("res/character/sword/swordWalkAnim.png", 24, 34, true));

			animationSet.put("sword", animations);



			animations = new HashMap<String, Animation>();
			animations.put("default", new Animation("res/enemy/goblin/goblinIdleAnim.png", 50, 10, true));
			animations.put("attacking", new Animation("res/enemy/goblin/goblinAttackAnim.png", 10, 10, false));
			animationSet.put("goblin", animations);

			animations = new HashMap<String, Animation>();
			animations.put("default", new Animation("res/enemy/famineBoss/famineBossIdleAnim.png", 50, 10, true));
			animations.put("attacking", new Animation("res/enemy/famineBoss/famineBossAttackAnim.png", 20, 10, false));
			animationSet.put("famineBoss", animations);
			
			animations = new HashMap<String, Animation>();
			animations.put("default", new Animation("res/enemy/zombie/zombieIdleAnim.png", 50, 10, true));
			animations.put("attacking", new Animation("res/enemy/zombie/zombieAttackAnim.png", 10, 10, false));
			animationSet.put("zombie", animations);

			animations = new HashMap<String, Animation>();
			animations.put("default", new Animation("res/enemy/hellHound/hellHoundIdleAnim.png", 50, 10, true));
			animations.put("attacking", new Animation("res/enemy/hellHound/hellHoundAttackAnim.png", 10, 10, false));
			animationSet.put("hellHound", animations);
			
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
