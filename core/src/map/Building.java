package map;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Random;

import graphics.Canvas.LayerType;
import graphics.GraphicComponent;
import physics.Collider;

public class Building
{

	public static void generate(int x, int y, String type)
	{
		try
		{
			File dir = new File("res/buildingGenPatterns/" + type);
			File[] directoryListing = dir.listFiles();
			int length = 0;
			if (directoryListing != null)
			{
				for (File file : directoryListing)
				{

					if (Integer.parseInt(file.getName().substring(7, file.getName().indexOf('.'))) > length)
					{
						length = Integer.parseInt(file.getName().substring(7, file.getName().indexOf('.')));
					}
				}
			}

			FileInputStream fstream = new FileInputStream(
					"res/buildingGenPatterns/" + type + "/pattern" + length + ".txt");
			BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

			int patternID = (new Random()).nextInt(length) + 1;
			int size = (int) (Float.parseFloat(br.readLine()) * Map.TILE_SIZE);

			new GraphicComponent(x, y, size, size, type + ":" + patternID, LayerType.BUILDINGS);

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

				Map.spawnElement(stringParts[indexToSpawn], x + elementX, y + elementY);
			}

			br.close();
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}
}
