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
	
	private TextComponent onHandInfoFlavor;
	private TextComponent relicInfoFlavor;
	
	
	
	public Inventory(int x, int y, int xSize, int ySize)
	{
		super(x, y, xSize, ySize);
		new GraphicComponent(x,y,xSize,ySize,"inventory",LayerType.GUI);
		onHandInfoPic = new GraphicComponent((int)(x+((float)xSize/2.0f)-((float)ITEM_SIZE/2.0f)),y+ySize-50,ITEM_SIZE,ITEM_SIZE,PlayerObject.getPlayerObject().getOnHand().getID(),LayerType.GUI);
		relicInfoPic = new GraphicComponent((int)(x+((float)xSize/2.0f)-((float)ITEM_SIZE/2.0f)),(int)(y+((float)ySize/2.0f)-50),ITEM_SIZE,ITEM_SIZE,PlayerObject.getPlayerObject().getRelic().getID(),LayerType.GUI);
		
		onHandInfoFlavor = new TextComponent(x,y+ySize-60,xSize,0,PlayerObject.getPlayerObject().getOnHand().getFlavorText(),LayerType.GUI);
		relicInfoFlavor = new TextComponent(x,(int)(y+((float)ySize/2.0f)-60),xSize,0,PlayerObject.getPlayerObject().getRelic().getFlavorText(),LayerType.GUI);
		
		
		
	}
	
	public void updateOnHand(OnHand newOnHandWeapon)
	{
		onHandInfoPic.updateTexture(newOnHandWeapon.getID());
		onHandInfoFlavor.updateText(newOnHandWeapon.getFlavorText());
	}
	public void updateRelic(Relic newRelic)
	{
		relicInfoPic.updateTexture(newRelic.getID());
		relicInfoFlavor.updateText(newRelic.getFlavorText());
	}

}
