package gameObjects;

import graphics.Canvas.LayerType;
import items.Item;
import items.OnHand;
import items.Relic;

public class PhysicalItem extends GameObject
{
	private Item associatedItem;

	public PhysicalItem(int x, int y, String texture, Item item)
	{
		super(x, y, 25, 25, texture, LayerType.ITEMS, false, false);
		associatedItem = item;
	}

	public Item getAssociatedItem()
	{
		return associatedItem;
	}

	public void replace(Item item)
	{
		gc.updateTexture(item.getID());
		associatedItem = item;
	}
}
