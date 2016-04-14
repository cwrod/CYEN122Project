package gameObjects;

import graphics.Canvas.LayerType;
import items.Item;

public abstract class Consumable extends GameObject {
	public Consumable(int x, int y, String texture, Item item)
	{
		super(x, y, 25, 25, texture, LayerType.ITEMS, false, false);
	}
	public abstract void useItem();
	
}
