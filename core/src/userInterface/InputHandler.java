package userInterface;

import java.awt.event.KeyEvent;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;

import gameObjects.PlayerObject;
import graphics.Canvas;

public class InputHandler implements InputProcessor
{
	
	
	public void update()
	{
		PlayerObject charObj = PlayerObject.getPlayerObject();
		if(Gdx.input.isKeyPressed(Keys.W))
			charObj.moveUp();
		if(Gdx.input.isKeyPressed(Keys.S))
			charObj.moveDown();
		if(Gdx.input.isKeyPressed(Keys.A))
			charObj.moveLeft();
		if(Gdx.input.isKeyPressed(Keys.D))
			charObj.moveRight();
		
		charObj.inputDone();
		
	}



	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}



	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}



	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}



	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		PlayerObject.getPlayerObject().attackLoc(screenX, screenY);
		return false;
	}



	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}



	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}



	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}



	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

	
	
}
