package map;

import java.util.Random;

public class TileDirt extends Tile
{

	public TileDirt(int tileSize) {
		super(tileSize);
	}
	
	@Override
	public String randomStyle()
	{
		Random r = new Random();
		if(r.nextBoolean())
		{
			return "dirt1";
		}else{
			return "dirt2";
		}
	}


}
