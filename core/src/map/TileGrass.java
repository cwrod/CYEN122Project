package map;

public class TileGrass extends Tile 
{

	public TileGrass(int tileSize) 
	{
		super(tileSize);
	}

	@Override
	public String randomStyle() 
	{
		return "grass";
	}

}
