package userInterface;

import java.util.Scanner;

import gameObjects.PlayerObject;
import map.Map;

public class Debug
{
	private boolean godMode = false;
	public void update()
	{
		Scanner scanner = new Scanner(System.in);
		if(scanner.hasNext())
		{
			String next = scanner.nextLine();
			try
			{
				String[] commands = next.split(" ");
				if(commands[0].equals("newItem"))
				{
					PlayerObject.getPlayerObject().equipItem(Map.getInstanceOfSpawnType(commands[1]));
				}
				if(commands[0].equals("spawnThing"))
				{
					if(commands.length==2)
					{
						Map.spawnElement(commands[1], PlayerObject.getPlayerObject().getX()+30, PlayerObject.getPlayerObject().getY(), null);
					}
					else
					{
						Map.spawnElement(commands[1], Integer.parseInt(commands[2]), Integer.parseInt(commands[3]), null);
					}
				}
				if(commands[0].equals("getLoc"))
				{
					System.out.println(">>>"+PlayerObject.getPlayerObject().getX() +"  "+PlayerObject.getPlayerObject().getY());
				}
				if(commands[0].equals("toggleGodMode"))
				{
					if(!godMode)
						PlayerObject.getPlayerObject().changeModDef(1000);
					else
						PlayerObject.getPlayerObject().changeModAtt(-1000);
				}
			}			
			catch(Exception e)
			{
				System.out.println("bad input");
			}
		}
		scanner.close();
	}
}
