package userInterface;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.Input.Keys;

import gameObjects.PlayerObject;

public class InputHandler
{

	public void update(boolean playableLevel)
	{
		if (playableLevel)
		{
			PlayerObject charObj = PlayerObject.getPlayerObject();
			if (Gdx.input.isKeyPressed(Keys.W))
				charObj.moveUp();
			if (Gdx.input.isKeyPressed(Keys.S))
				charObj.moveDown();
			if (Gdx.input.isKeyPressed(Keys.A))
				charObj.moveLeft();
			if (Gdx.input.isKeyPressed(Keys.D))
				charObj.moveRight();
			if (Gdx.input.isKeyPressed(Keys.F))
				charObj.pickUp();
			

			if (Gdx.input.isButtonPressed(Buttons.LEFT))
				PlayerObject.getPlayerObject().attackLoc(Gdx.input.getX(), Gdx.input.getY());

			charObj.inputDone();
		}
	}
}
