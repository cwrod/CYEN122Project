package items;

import gameObjects.PhysicalItem;
import graphics.TextComponent;
import toolbox.Functions;

public abstract class Item
{
	protected String id;
	protected String type;
	protected String flavorText;
	private String name;

	public Item(int x, int y, String id, String type, String flavorText)
	{
		this(id,type, flavorText);
		new PhysicalItem(x, y, id, this);
	}

	public Item(String id, String type, String flavorText)
	{
		this.id = id;
		this.flavorText = Functions.formatString(flavorText, TextComponent.LINE_SIZE);
		this.type = type;

		name = Functions.camelCaseString(id);
	}

	public String getID()
	{
		return id;
	}
	public String getType()
	{
		return type;
	}

	public String getFlavorText()
	{
		return flavorText;
	}

	public String getName()
	{
		return name;
	}

	public void equip(){}
	
	public void dequip(){}
}
