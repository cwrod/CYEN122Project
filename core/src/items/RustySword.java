package items;

import gameObjects.Building;

public class RustySword extends OnHand
{

	public RustySword(int x, int y, Building owner)
	{
		super(5, 20, "rustySword","The sword version of a Tyler Perry movie.", x, y);

	}

	public RustySword()
	{
		super(5, 20, "rustySword","The sword version of a Tyler Perry movie.");
	}

}
