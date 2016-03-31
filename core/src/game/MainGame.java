package game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import ai.EnemyHandler;
import gameObjects.Goblin;
import gameObjects.PlayerObject;
import graphics.Canvas;
import graphics.ImageLibrary;
import map.Map;
import toolbox.Time;
import userInterface.InputHandler;

/*
 * The main entry point for the game. This handles the game after the Desktop Launcher
 * sets up the actual game screen. All loading of game assets should go here. Progressing 
 * through the game and loading new levels will probably also end up here.
 */
public class MainGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	
	InputHandler ih;
	
	
	/*
	 * A method from ApplicationAdaptor that gets called when the game screen is made.
	 */
	@Override
	public void create () 
	{
		batch = new SpriteBatch();
		load();
    }
	
	
	/*
	 * Calls singletons, loads map, and processes images for the game
	 */
    public void load()
    {
    	ImageLibrary.getImageLibrary();
    	Canvas.getCanvas();	
    	PlayerObject.getPlayerObject();
    	new Map(50,50);

    	ih = new InputHandler();
    	Gdx.input.setInputProcessor(ih);
    	EnemyHandler.getEnemyHandler().spawnEnemies(new Goblin(20, 20, 25, 25));;
    	Time.getTime();
    	
	}

    /*
     * Another method from libgdx. Gets called every frame. All updates should be called from here.
     */
	@Override
	public void render () {
	    Time.getTime().tick();
		ih.update();
		EnemyHandler.getEnemyHandler().update();
		
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		Canvas.getCanvas().paint(batch);
		batch.end();
	}
}
