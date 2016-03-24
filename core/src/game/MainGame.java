package game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import ai.EnemyHandler;
import gameObjects.CharacterObject;
import gameObjects.GameObject;
import gameObjects.Goblin;
import graphics.Canvas;
import graphics.ImageLibrary;
import map.Map;
import toolbox.Time;
import userInterface.KeyboardHandler;

public class MainGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	
	@Override
	public void create () 
	{
		batch = new SpriteBatch();
		load();
    }

    public static void load()
    {
    	ImageLibrary.loadImages();
    	Canvas.getCanvas();
    	CharacterObject.getCharacterObject();
    	Map m = new Map(50,50);
    	EnemyHandler.getEnemyHandler().spawnEnemies(new Goblin(50, 100, 25, 25));
	}

	@Override
	public void render () {
	    Time.getTime().tick();
		KeyboardHandler.getKeyboardHandler().update();
		EnemyHandler.getEnemyHandler().update();
		
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		Canvas.getCanvas().paint(batch);
		batch.end();
	}
}
