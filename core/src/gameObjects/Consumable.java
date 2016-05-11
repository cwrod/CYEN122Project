package gameObjects;

import graphics.Canvas.LayerType;
import items.Item;

public abstract class Consumable extends GameObject {
	public Consumable(int x, int y, String texture)
	{
		super(x, y, 15, 15, texture, LayerType.ITEMS, false, false);
	}
	public void useItem()
	{
		onItemUse();
		this.kill();
	}
	protected abstract void onItemUse();
	
}
