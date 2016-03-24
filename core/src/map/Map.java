package map;

import java.util.Random;

public class Map {
	private int mapSize; //in tiles
	
	private int tileSize; //in pixels
	
	private Tile[][] map;
	
	public Map(int mapSize,int tileSize)
	{
		this.mapSize=mapSize;
		this.tileSize=tileSize;
		
		map = new Tile[mapSize][mapSize];
		for(int i = 0; i < map.length; i++)
		{
			for(int j = 0; j < map[i].length; j++)
			{
				Tile t = randomGen();
				t.setPos(i*tileSize, j*tileSize);
				map[i][j] = t;
			}
		}
		
	}
	
	public Tile randomGen()
	{
		Random r = new Random();
		if(r.nextBoolean())
		{
			return new TileDirt(tileSize);
		}else{
			return new TileGrass(tileSize);
		}
	}
	
	
}
