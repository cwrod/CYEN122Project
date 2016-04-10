package map;

import java.util.HashMap;
import java.util.Random;

import gameObjects.Building;
import gameObjects.Goblin;
import graphics.Canvas.LayerType;
import graphics.GraphicComponent;
import graphics.ImageLibrary;
import items.LegendarySword;

public class Map
{

	public static final int TILE_SIZE = 50; // in pixels

	// WARNING: EXTREMELY VOLATILE WAY TO DO THIS!
	private static HashMap<String, Class<?>> spawnTypes;

	public static void initSpawnTypes()
	{
		spawnTypes = new HashMap<String, Class<?>>();

		spawnTypes.put("goblin", Goblin.class);
		spawnTypes.put("legendarySword", LegendarySword.class);

	}

	public static void generate(int mapSize, String level)
	{
		for (int i = 0; i < mapSize; i++)
		{
			for (int j = 0; j < mapSize; j++)
			{
				new GraphicComponent(i * TILE_SIZE, j * TILE_SIZE, Map.TILE_SIZE, Map.TILE_SIZE, randomStyle(level),
						LayerType.BACKGROUND);

			}
		}

		BuildingHandler.getBuildingHandler().generateLevel(level);

	}

	private static String randomStyle(String level)
	{
		Random r = new Random();
		return level + "-" + (r.nextInt(ImageLibrary.getImageLibrary().lengthOfSet(level)) + 1);
	}

	public static void spawnElement(String key, int elementX, int elementY,Building owner)
	{
		try
		{
			Class<?>[] args = { int.class, int.class, Building.class};
			spawnTypes.get(key).getDeclaredConstructor(args).newInstance(elementX, elementY, owner);
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}
}
