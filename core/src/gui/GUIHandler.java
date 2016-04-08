package gui;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import graphics.GraphicComponent;

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

	private ArrayList<GUIComponent> elements;

	private HealthBar health;

	private void init()
	{
		health = new HealthBar(30, 30, 200, 20);
		elements = new ArrayList<GUIComponent>();
		elements.add(health);
	}

	public void updateHealth(float healthPercent)
	{
		health.updateHealth(healthPercent);
	}
}