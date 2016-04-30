package gui;

import game.MainGame;
import game.MainGame.Level;
import graphics.Canvas;
import graphics.Canvas.LayerType;
import graphics.GraphicComponent;
import graphics.TextComponent;
import toolbox.DeltaTime;

public class PauseScreen extends GUIComponent
{

	public PauseScreen()
	{
		super(0, 0, Canvas.WIDTH, Canvas.HEIGHT);
		addComponentToKill(new GraphicComponent(0, 0, Canvas.WIDTH, Canvas.HEIGHT, "transparency", LayerType.GUI));
		Button returnToGame = new Button(200, 250, 100, 20, "inventory");
		returnToGame.addButtonListener(new ButtonListener()
				{
					@Override
					public void onButtonPressed(Button b)
					{
						DeltaTime.togglePause();
					}
				});
		addComponentToKill(returnToGame);
		addComponentToKill(new TextComponent(200, 265, 100, 20, "Return to Game", LayerType.GUI));
		
		GUIHandler.getGUIHandler().addInteractableObject(returnToGame);
		
		Button load = new Button(200, 200, 100, 20, "inventory");
		load.addButtonListener(new ButtonListener()
		{
			@Override
			public void onButtonPressed(Button b)
			{
				MainGame.getMainGame().getGameData().load();
			}
		});
		addComponentToKill(load);
		addComponentToKill(new TextComponent(200, 215, 100, 20, "Load", LayerType.GUI));
		GUIHandler.getGUIHandler().addInteractableObject(load);
		
		Button mainMenu = new LevelLoadButton(200, 150, 100, 20, "inventory",Level.MAIN_MENU);
		addComponentToKill(mainMenu);
		addComponentToKill(new TextComponent(200, 165, 100, 20, "Main Menu", LayerType.GUI));
		GUIHandler.getGUIHandler().addInteractableObject(mainMenu);
		


	}

}
