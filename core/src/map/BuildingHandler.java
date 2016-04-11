package map;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

import gameObjects.Building;
import physics.Collider;
import quest.QuestHandler;

public class BuildingHandler
{

	// Basic header for a singleton

	private static BuildingHandler buildingHandlerSingleton;

	public static BuildingHandler getBuildingHandler()
	{
		if (buildingHandlerSingleton == null)
		{
			buildingHandlerSingleton = new BuildingHandler();
		}

		return buildingHandlerSingleton;
	}
	public static void reset()
	{
		buildingHandlerSingleton = new BuildingHandler();
	}

	private ArrayList<Building> buildings;

	public BuildingHandler()
	{
		buildings = new ArrayList<Building>();
	}

	public void generateLevel(String level)
	{
		generate(100, 500, "stone", false);
		generate(1000, 100, "stone", true);

	}

	private void generate(int x, int y, String type, boolean isBossLair)
	{
		try
		{
			String patternID;
			if (!isBossLair)
			{
				File dir = new File("res/buildingGenPatterns/" + type);
				File[] directoryListing = dir.listFiles();
				int length = 0;
				if (directoryListing != null)
				{
					for (File file : directoryListing)
					{
						try
						{
							if (Integer.parseInt(file.getName().substring(7, file.getName().indexOf('.'))) > length)
							{
								length = Integer.parseInt(file.getName().substring(7, file.getName().indexOf('.')));
							}
						}
						catch (Exception e)
						{
						}
					}
				}
				patternID = Integer.toString(((new Random()).nextInt(length) + 1));
			}
			else
			{
				patternID = "BOSS";
			}
			FileInputStream fstream = new FileInputStream(
					"res/buildingGenPatterns/" + type + "/pattern" + patternID + ".txt");
			BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

			int size = (int) (Float.parseFloat(br.readLine()) * Map.TILE_SIZE);

			Building building = new Building(x, y, size, size, type + ":" + patternID, !isBossLair);
			if (isBossLair)
				QuestHandler.getQuestHandler().setBossLair(building);

			String strLine;

			while (!(strLine = br.readLine()).equals("END"))
			{
				String[] stringParts = strLine.split(",");
				int[] colliderParams = new int[4];
				for (int i = 0; i < stringParts.length; i++)
				{
					colliderParams[i] = (int) (Float.parseFloat(stringParts[i]) * size);
				}
				new Collider(x + colliderParams[0], y + colliderParams[1], colliderParams[2], colliderParams[3]);
			}

			while ((strLine = br.readLine()) != null)
			{
				String[] stringParts = strLine.split(":");
				int elementX = (int) (Float.parseFloat(stringParts[0]) * size);
				int elementY = (int) (Float.parseFloat(stringParts[1]) * size);

				int indexToSpawn = (new Random()).nextInt(stringParts.length - 2) + 2;

				Map.spawnElement(stringParts[indexToSpawn], x + elementX, y + elementY, building);
			}

			br.close();
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}

	public void update()
	{
		for (Building b : buildings)
		{
			b.update();
		}
	}

	public void add(Building b)
	{
		buildings.add(b);
	}

	public void remove(Building b)
	{
		buildings.remove(b);
	}


}
