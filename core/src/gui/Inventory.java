package gui;

import gameObjects.PlayerObject;
import graphics.Canvas.LayerType;
import graphics.GraphicComponent;
import graphics.TextComponent;
import items.OnHand;
import items.Relic;

public class Inventory extends GUIComponent
{
	public static final int ITEM_SIZE = 25;

	private GraphicComponent onHandInfoPic;
	private GraphicComponent relicInfoPic;

	private TextComponent onHandName;
	private TextComponent relicName;

	private TextComponent onHandInfoFlavor;
	private TextComponent relicInfoFlavor;

	private Button minimize;
	private GraphicComponent background;
	public Inventory(int x, int y, int xSize, int ySize)
	{
		super(x, y, xSize, ySize);
		background = new GraphicComponent(x, y, xSize, ySize, "inventory", LayerType.GUI);
		onHandInfoPic = new GraphicComponent((int) (x + ((float) xSize / 2.0f) - ((float) ITEM_SIZE / 2.0f)),
				y + ySize - 50, ITEM_SIZE, ITEM_SIZE, PlayerObject.getPlayerObject().getOnHand().getID(),
				LayerType.GUI);
		relicInfoPic = new GraphicComponent((int) (x + ((float) xSize / 2.0f) - ((float) ITEM_SIZE / 2.0f)),
				(int) (y + ((float) ySize / 2.0f) - 50), ITEM_SIZE, ITEM_SIZE,
				PlayerObject.getPlayerObject().getRelic().getID(), LayerType.GUI);

		
		onHandName = new TextComponent(x, y + ySize - 60, xSize, 0,
				PlayerObject.getPlayerObject().getOnHand().getName(), LayerType.GUI);
		onHandInfoFlavor = new TextComponent(x, y + ySize - 80, xSize, 0,
				PlayerObject.getPlayerObject().getOnHand().getFlavorText(), LayerType.GUI);
		

		relicName = new TextComponent(x, (int) (y + ((float) ySize / 2.0f) - 60), xSize, 0,
				PlayerObject.getPlayerObject().getRelic().getName(), LayerType.GUI);
		relicInfoFlavor = new TextComponent(x, (int) (y + ((float) ySize / 2.0f) - 80), xSize, 0,
				PlayerObject.getPlayerObject().getRelic().getFlavorText(), LayerType.GUI);

		minimize = new Button(x+2,y+ySize-22,20,20,"minimize");
		minimize.addButtonListener(new ButtonListener()
				{

					@Override
					public void onButtonPressed(Button b)
					{
						GUIHandler.getGUIHandler().minimizeInventory();
					}
			
				});
		
		GUIHandler.getGUIHandler().addInteractableObject(minimize);
	}

	public void updateOnHand(OnHand newOnHandWeapon)
	{
		onHandInfoPic.updateTexture(newOnHandWeapon.getID());
		onHandName.updateText(newOnHandWeapon.getName());
		onHandInfoFlavor.updateText(newOnHandWeapon.getFlavorText());
	}

	public void updateRelic(Relic newRelic)
	{
		relicInfoPic.updateTexture(newRelic.getID());
		relicName.updateText(newRelic.getName());		
		relicInfoFlavor.updateText(newRelic.getFlavorText());
	}
	
	@Override public void kill()
	{
		onHandInfoPic.kill();
		relicInfoPic.kill();

		onHandName.kill();
		relicName.kill();

		onHandInfoFlavor.kill();
		relicInfoFlavor.kill();

		minimize.kill();
		GUIHandler.getGUIHandler().removeInteractableObject(minimize);
		background.kill();
		super.kill();
	}

}
