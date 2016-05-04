package com.mygdx.game.desktop;

import com.badlogic.gdx.Files.FileType;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import game.MainGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.height = 500;
		config.width = 500;
		config.title = "Journey Through Hell: Dr. Kiremire's Quest for Tenure";
		config.addIcon("res/misc/title128by128.png", FileType.Internal);
		config.addIcon("res/misc/title32by32.png", FileType.Internal);
		config.addIcon("res/misc/title16by16.png", FileType.Internal);
		
		new LwjglApplication(new MainGame(), config);
	}
}
