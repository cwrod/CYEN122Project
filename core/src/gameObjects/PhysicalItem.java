package gameObjects;

import graphics.Canvas.LayerType;
import items.OnHand;
import items.Relic;

public class PhysicalItem extends GameObject
{
	private OnHand associatedItem;

	public PhysicalItem(int x, int y, String texture, OnHand oh)
	{
		super(x, y, 25, 25, texture, LayerType.ITEMS, false, false);
		associatedItem = oh;
	}

	public OnHand getAssociatedItem()
	{
		return associatedItem;
	}

	public void replace(OnHand oh)
	{
		gc.updateTexture(oh.getAnimName());
		associatedItem = oh;
	}
}
