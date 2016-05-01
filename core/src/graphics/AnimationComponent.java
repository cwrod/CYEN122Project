package graphics;

import java.util.HashMap;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

import graphics.Canvas.LayerType;

/*
 * A graphic component that uses animations instead of still images.
 * 
 */
public class AnimationComponent extends GraphicComponent
{

	private HashMap<String, Animation> animations;

	private String currentTrack;
	private String defaultTrack;
	
	private boolean freezeOnEnd = false;

	public AnimationComponent(int x, int y, int xSize, int ySize, String key, LayerType layer)
	{
		super(x, y, xSize, ySize, "animError", layer);
		animations = new HashMap<String, Animation>();
		for(String animation : ImageLibrary.getImageLibrary().findAnim(key).keySet())
		{
			animations.put(animation, new Animation(ImageLibrary.getImageLibrary().findAnim(key).get(animation)));
		}
		defaultTrack = currentTrack = "default";


	}

	public boolean isDone(String track)
	{
		if(freezeOnEnd)
			return false;
		if(!currentTrack.equals(track))
			return true;
		if (animations.get(track).isDone())
		{
			updateTexture(defaultTrack);
			return true;
		}
		return false;
	}
	public void freezeOnEnd()
	{
		freezeOnEnd = true;
	}

	/*
	 * Overrides the getTexture to actually return the frame of the currently
	 * playing animation.
	 */
	@Override
	public TextureRegion getTexture()
	{

		isDone(currentTrack);
		return animations.get(currentTrack).play();
	}

	/*
	 * Instead of changing the texture, change the entire animation.
	 */
	@Override
	public void updateTexture(String key)
	{
		currentTrack = key;
		animations.get(currentTrack).play();
	}

	public void updateSet(String key)
	{
		animations = ImageLibrary.getImageLibrary().findAnim(key);
	}
	public void setDefaultTrack(String key)
	{
		defaultTrack = key;
	}

	public String getCurrentTrack()
	{
		return currentTrack;
	}

}
