package gui;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import graphics.Canvas;
import graphics.GraphicComponent;
import items.OnHand;
import items.Relic;

public class GUIHandler
{

	// Basic Singleton header

	private static GUIHandler guiHandlerSingleton;

	public static GUIHandler getGUIHandler()
	{
		if (guiHandlerSingleton == null)
		{
			guiHandlerSingleton = new GUIHandler();
		}
		return guiHandlerSingleton;
	}

	public GUIHandler()
	{
		init();
	}


	private HealthBar health;
	private Inventory inventory;
	

	private void init()
	{
		health = new HealthBar(30, 30, 200, 20);
		inventory = new Inventory(Canvas.WIDTH - 150, 100, 140,300);
	}

	public void updateHealth(float healthPercent)
	{
		health.updateHealth(healthPercent);
	}
	public void updateOnHand(OnHand newOnHandWeapon)
	{
		inventory.updateOnHand(newOnHandWeapon);
	}
	public void updateRelic(Relic newRelic)
	{
		inventory.updateRelic(newRelic);
	}
}