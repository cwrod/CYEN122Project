package userInterface;

import java.awt.event.KeyEvent;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;

import gameObjects.CharacterObject;
import graphics.Canvas;

public class KeyboardHandler
{
	private static KeyboardHandler keyboardHandlerSingleton;

	public static KeyboardHandler getKeyboardHandler()
	{
		if(keyboardHandlerSingleton==null)
		{
			keyboardHandlerSingleton = new KeyboardHandler();
		}
		return keyboardHandlerSingleton;
	}

	
	
	public void update()
	{
		CharacterObject charObj = CharacterObject.getCharacterObject();
		if(Gdx.input.isKeyPressed(Keys.W))
			charObj.moveUp();
		if(Gdx.input.isKeyPressed(Keys.S))
			charObj.moveDown();
		if(Gdx.input.isKeyPressed(Keys.A))
			charObj.moveLeft();
		if(Gdx.input.isKeyPressed(Keys.D))
			charObj.moveRight();
		
	}

	
	
}
