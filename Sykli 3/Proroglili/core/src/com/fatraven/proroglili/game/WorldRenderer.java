package com.fatraven.proroglili.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class WorldRenderer {
	private SpriteBatch batch;
	private OrthographicCamera camera;
	private WorldController worldController;
	private FitViewport viewport;
	private BitmapFont font;
	
	public WorldRenderer(WorldController worldController){
		this.worldController = worldController;
		batch = new SpriteBatch();
		camera = new OrthographicCamera(); //240/180 ja testissä 800/600
		viewport = new FitViewport(
				1000, 
				800,
				camera);
		camera.position.set(500, 400, 0);
		camera.update();
		font = new BitmapFont();
		font.scale(0.5f);
	}
	
	public void render(){
		if (Gdx.input.isKeyJustPressed(Keys.Z)){
			camera.zoom -= 0.25f;
			camera.update();
		}
		if (Gdx.input.isKeyJustPressed(Keys.X)){
			camera.zoom += 0.25f;
			camera.update();
		}
		if (Gdx.input.isKeyJustPressed(Keys.LEFT)){
			camera.position.x -= 8;
			camera.update();
		}
		if (Gdx.input.isKeyJustPressed(Keys.RIGHT)){
			camera.position.x += 8;
			camera.update();
		}
		if (Gdx.input.isKeyJustPressed(Keys.DOWN)){
			camera.position.y -= 8;
			camera.update();
		}
		if (Gdx.input.isKeyJustPressed(Keys.UP)){
			camera.position.y += 8;
			camera.update();
		}
		batch.setProjectionMatrix(camera.combined);
		Gdx.gl20.glClearColor(0.0f, 1.0f, 0.0f, 1.0f);
		Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);	
		batch.disableBlending();
		batch.begin();
		renderWorld();
		batch.end();
	}

	private void renderWorld() {
		worldController.level.draw(batch);
		/*font.draw(batch, "x: " + Gdx.input.getX() + " y: " + (Gdx.input.getY() * -1 + 600), 
				Gdx.input.getX(), Gdx.input.getY() * -0.25f + 600 + 20);
		font.draw(batch, "[y][x] [" + Gdx.input.getX() / 8 + "][" + (Gdx.input.getY() * -1 + 600) / 8 + "]", 
				Gdx.input.getX(), Gdx.input.getY() * -0.25f + 600 + 10);*/
	}
	
	public void resize(int width, int height){
		viewport.update(width, height);
		camera.update();
	}
}
