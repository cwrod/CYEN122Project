package graphics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import toolbox.DeltaTime;

/*
 * A singular moving picture, just like those newfangled city-folk have.
 */
public class Animation
{
	private TextureRegion[] frames;
	private int currentFrame;
	private int animationLength;

	private double frameChange;
	private double timeCounter;

	private boolean shouldLoop;
	private boolean isDone;

	public Animation(String source, int fps, int animationLength, boolean shouldLoop)
	{
		this.frameChange = 1.0f / fps;
		this.animationLength = animationLength;
		this.shouldLoop = shouldLoop;
		currentFrame = 0;
		isDone = true;
		frames = new TextureRegion[animationLength];
		Texture texture = new Texture(source);
		for (int i = 0; i < animationLength; i++)
		{

			frames[i] = new TextureRegion(texture, i * (1.0f / animationLength), 0,
					(i * (1.0f / animationLength)) + (1.0f / animationLength), 1.0f);
		}
	}

	/*
	 * Returns the current frame of the animation
	 */
	public TextureRegion play()
	{
		isDone = false;
		timeCounter += DeltaTime.get();
		while (timeCounter > frameChange)
		{
			timeCounter -= frameChange;
			currentFrame++;
			if (currentFrame == animationLength)
			{
				if (shouldLoop)
				{
					currentFrame = 0;
				}
				else
				{
					currentFrame--;
					isDone = true;
				}
			}
		}
		return frames[currentFrame];

	}

	/*
	 * On a non-looping animation, end the animation.
	 */
	public boolean isDone()
	{
		if (isDone)
		{
			currentFrame = 0;
			return true;
		}
		return false;
	}

}
