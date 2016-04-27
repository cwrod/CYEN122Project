package gui;

import game.MainGame;
import graphics.Canvas;
import graphics.Canvas.LayerType;
import graphics.GraphicComponent;

public class PauseScreen extends GUIComponent
{

	public PauseScreen()
	{
		super(0, 0, Canvas.WIDTH, Canvas.HEIGHT);
		addComponentToKill(new GraphicComponent(0, 0, Canvas.WIDTH, Canvas.HEIGHT, "transparency", LayerType.GUI));
		Button save = new Button(200, 250, 100, 20, "inventory");
		save.addButtonListener(new ButtonListener()
				{
					@Override
					public void onButtonPressed(Button b)
					{
						MainGame.getMainGame().getGameData().save();
					}
				});
		addComponentToKill(save);
		GUIHandler.getGUIHandler().addInteractableObject(save);
		
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
		GUIHandler.getGUIHandler().addInteractableObject(load);
		
		Button exit = new Button(200, 150, 100, 20, "inventory");
		exit.addButtonListener(new ButtonListener()
		{
			@Override
			public void onButtonPressed(Button b)
			{
				System.exit(0);
			}
		});
		addComponentToKill(exit);
		GUIHandler.getGUIHandler().addInteractableObject(exit);
		


	}

}
