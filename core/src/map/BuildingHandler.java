package map;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

import gameObjects.Building;
import gameObjects.GameObject;
import gameObjects.PlayerObject;
import physics.Collider;
import quest.QuestHandler;
import toolbox.Functions;

public class BuildingHandler
{
	
	public static final int BUILDING_MAX_SIZE = 12;

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

	public void generateLevel(String level, int mapSize)
	{
		int modifiedSize = (int)(mapSize-(mapSize*0.2f));
		generateField(level+"building",10,modifiedSize);
	}
	
	private void generateField(String type, int number, int mapSize)
	{
		ArrayList<int[]> dimensions = new ArrayList<int[]>();
		dimensions.add(new int[]{PlayerObject.getPlayerObject().getX(),PlayerObject.getPlayerObject().getY(),PlayerObject.getPlayerObject().getX()+PlayerObject.WIDTH,PlayerObject.getPlayerObject().getY()+PlayerObject.HEIGHT});
		for(int i = 0; i < number; i++)
		{
			Random r = new Random();
			boolean spaceOccupied = true;
			int xLeft = 0;
			int yBottom = 0;
			int xRight = 0;
			int yTop = 0;
			int counter = 0;
			while(spaceOccupied)
			{
				
				//This is such a shitty way to do this. I am fucking ashamed with myself.
				counter++;
				if(counter > 200)
				{
					System.out.println("Only " + i + " buildings were made out of "+number);
					return;
				}
				xLeft = r.nextInt(mapSize);
				yBottom = r.nextInt(mapSize);
				xRight = xLeft + (BUILDING_MAX_SIZE*Map.TILE_SIZE);
				yTop = yBottom + (BUILDING_MAX_SIZE*Map.TILE_SIZE);
				spaceOccupied = false;
				for(int[] dimension : dimensions)
				{
					if ((dimension[0] >= xLeft && dimension[0] <= xRight) || (dimension[2] >= xLeft && dimension[2] <= xRight))
					{
						if ((dimension[1] >= yBottom && dimension[1] <= yTop)||(dimension[3] >= yBottom && dimension[3] <= yTop))
						{
							spaceOccupied = true;
						}	
					}
				}
			}
			int [] newBuildingDimensions = {xLeft,yBottom,xRight,yTop};
			dimensions.add(newBuildingDimensions);
			generate(xLeft,yBottom,type,i == 0);
		}
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

			Building building = new Building(x, y, size, size, type + ":" + patternID, !isBossLair, isBossLair);
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
			e.printStackTrace();
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
	public Building closestNonBossBuilding(GameObject go)
	{
		Building minBuild = buildings.get(0);
		for (Building b : buildings)
		{
			if(Functions.distance(go, b)<Functions.distance(go, minBuild))
			{
				if(!b.isBossLair())
					minBuild = b;
			}
		}
		return minBuild;
	}


}
