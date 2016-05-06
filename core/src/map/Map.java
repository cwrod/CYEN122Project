package map;

import java.util.HashMap;
import java.util.Random;

import gameObjects.Building;
import gameObjects.ClearDoor;
import gameObjects.DeathBoss;
import gameObjects.DemonDeath;
import gameObjects.DemonFamine;
import gameObjects.DemonPlague;
import gameObjects.DemonWar;
import gameObjects.Elixr;
import gameObjects.FamineBoss;
import gameObjects.GiantRat;
import gameObjects.Goblin;
import gameObjects.HellHound;
import gameObjects.PlagueBoss;
import gameObjects.WarBoss;
import gameObjects.Zombie;
import graphics.Canvas.LayerType;
import graphics.GraphicComponent;
import graphics.ImageLibrary;
import items.AngelicBlade;
import items.AxeOfTheFallenViking;
import items.BladeOfWar;
import items.Bow;
import items.HolyWater;
import items.IronSword;
import items.Item;
import items.LegendarySword;
import items.Rosary;
import items.RustySword;
import items.SkeletonsArm;
import items.TheHandOfDeath;
import items.TheLightBringer;
import physics.Collider;

public class Map
{

	public static final int TILE_SIZE = 50; // in pixels

	// WARNING: EXTREMELY VOLATILE WAY TO DO THIS!
	private static HashMap<String, Class<?>> spawnTypes;

	public static void initSpawnTypes()
	{
		spawnTypes = new HashMap<String, Class<?>>();

		spawnTypes.put("goblin", Goblin.class);
		spawnTypes.put("zombie", Zombie.class);
		spawnTypes.put("legendarySword", LegendarySword.class);
		spawnTypes.put("rustySword", RustySword.class);
		spawnTypes.put("holyWater", HolyWater.class);
		spawnTypes.put("clearDoor", ClearDoor.class);
		spawnTypes.put("famineBoss", FamineBoss.class);
		spawnTypes.put("plagueBoss", PlagueBoss.class);
		spawnTypes.put("warBoss", WarBoss.class);		
		spawnTypes.put("deathBoss", DeathBoss.class);		
		spawnTypes.put("hellHound", HellHound.class);
		spawnTypes.put("bow", Bow.class);
		spawnTypes.put("giantRat", GiantRat.class);
		spawnTypes.put("angelicBlade", AngelicBlade.class);
		spawnTypes.put("axeOfTheFallenViking", AxeOfTheFallenViking.class);
		spawnTypes.put("bladeOfWar", BladeOfWar.class);
		spawnTypes.put("ironSword", IronSword.class);
		spawnTypes.put("skeletonsArm", SkeletonsArm.class);
		spawnTypes.put("theHandOfDeath", TheHandOfDeath.class);
		spawnTypes.put("theLightBringer", TheLightBringer.class);
		spawnTypes.put("demonFamine", DemonFamine.class);
		spawnTypes.put("demonPlague", DemonPlague.class);
		spawnTypes.put("demonWar", DemonWar.class);
		spawnTypes.put("demonDeath", DemonDeath.class);
		spawnTypes.put("rosary", Rosary.class);
		spawnTypes.put("elixr", Elixr.class);
		
		
		
		
		
		
		

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

		BuildingHandler.getBuildingHandler().generateLevel(level,mapSize*TILE_SIZE);
		new Collider(0, -20, mapSize*TILE_SIZE + 20, 20); //Bottom
		new Collider(-20, 0, 20,mapSize*TILE_SIZE + 20); //Left
		new Collider(0, mapSize*TILE_SIZE, mapSize*TILE_SIZE,20); //Top
		new Collider(mapSize*TILE_SIZE, 0,20,mapSize*TILE_SIZE); //Right

	}

	private static String randomStyle(String level)
	{
		Random r = new Random();
		return level + "-" + (r.nextInt(ImageLibrary.getImageLibrary().lengthOfSet(level)) + 1);
	}

	public static void spawnElement(String key, int elementX, int elementY, Building owner)
	{
		if(key.equals("null"))
			return;
		try
		{
			Class<?>[] args = { int.class, int.class, Building.class };
			spawnTypes.get(key).getDeclaredConstructor(args).newInstance(elementX, elementY, owner);
		}
		catch (Exception e)
		{
			System.out.println(key);
			e.printStackTrace();
		}
	}

	public static Item getInstanceOfSpawnType(String key)
	{
		try
		{
			return (Item) spawnTypes.get(key).getDeclaredConstructor().newInstance();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
}
