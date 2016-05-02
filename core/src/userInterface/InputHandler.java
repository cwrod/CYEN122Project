package userInterface;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.Input.Keys;

import gameObjects.PlayerObject;
import graphics.Canvas;
import gui.GUIHandler;
import gui.PrayerBar;
import toolbox.DeltaTime;

public class InputHandler
{

	private Debug debugger = new Debug();
	public void update(boolean playableLevel)
	{
		if (playableLevel)
		{
			if(Gdx.input.isKeyPressed(Keys.C))
			{
				boolean wasPaused = DeltaTime.isPaused();
				if(wasPaused)
					DeltaTime.togglePause();
				debugger.update();
				if(wasPaused)
					DeltaTime.togglePause();
				return;
			}
			if(!DeltaTime.isPaused())
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
				
				
				PrayerBar pb = GUIHandler.getGUIHandler().getPrayerBar();
				if (Gdx.input.isKeyPressed(Keys.NUM_1))
					pb.prayerCalled(0);
				if (Gdx.input.isKeyPressed(Keys.NUM_2))
					pb.prayerCalled(1);
				if (Gdx.input.isKeyPressed(Keys.NUM_3))
					pb.prayerCalled(2);
				if (Gdx.input.isKeyPressed(Keys.NUM_4))
					pb.prayerCalled(3);
				

	
				if (Gdx.input.isButtonPressed(Buttons.LEFT))
				{
					int x = Gdx.input.getX();
					int y = Canvas.HEIGHT-Gdx.input.getY();
					if(!GUIHandler.getGUIHandler().wasGUIPressed(x,y))
						PlayerObject.getPlayerObject().attackLoc(x, y);
				}
				charObj.inputDone();			
			}
			else
			{
				if (Gdx.input.isButtonPressed(Buttons.LEFT))
				{
					int x = Gdx.input.getX();
					int y = Canvas.HEIGHT-Gdx.input.getY();
					GUIHandler.getGUIHandler().wasGUIPressed(x,y);
				}
			}
			if(Gdx.input.isKeyJustPressed(Keys.P))
			{
				DeltaTime.togglePause();
			}
		}
		else
		{
			if (Gdx.input.isButtonPressed(Buttons.LEFT))
			{
				int x = Gdx.input.getX();
				int y = Canvas.HEIGHT-Gdx.input.getY();
				GUIHandler.getGUIHandler().wasGUIPressed(x,y);
					
			}
		}
		
	}
}
