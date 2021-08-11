package com.fatraven.vaalipeli.game;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class WorldRenderer implements Disposable{
	public static final String TAG = WorldRenderer.class.getName();
	
	private WorldController controller;
	private SpriteBatch batch;
	private OrthographicCamera camera;
	private FitViewport viewport;
	
	public WorldRenderer(WorldController controller){
		this.controller = controller;
		init();
	}
	
	private void init(){
		batch = new SpriteBatch();
		camera = new OrthographicCamera();
		viewport = new FitViewport(80, 60, camera);
		camera.position.set(0, 0, 0); //voit kokeilla myös 40,30,0
		camera.update();
	}
	
	public void render(){
		
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		renderWorld(batch);
		batch.end();
		renderGameWorldGui();
	}
	
	private void renderGameWorldGui() {
		controller.getLevel().renderGUI();
	}

	public void resize(int width, int height){
		viewport.update(width, height);
		camera.update();
	}
	
	private void renderWorld(SpriteBatch batch){
		controller.getLevel().render(batch);
	}
	
	public void dispose(){
		batch.dispose();
	}
}
