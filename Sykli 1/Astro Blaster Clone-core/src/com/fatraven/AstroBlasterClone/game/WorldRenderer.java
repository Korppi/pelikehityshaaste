package com.fatraven.AstroBlasterClone.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.fatraven.AstroBlasterClone.util.Constants;

public class WorldRenderer implements Disposable{
	public static final String TAG = WorldRenderer.class.getName();
	private SpriteBatch batch;
	private OrthographicCamera camera;
	private OrthographicCamera cameraGUI;
	private WorldController worldController;
	private FitViewport viewport;
	private FitViewport viewportGUI;
	
	public WorldRenderer(WorldController worldController){
		this.worldController = worldController;
		init();
	}
	
	public void init(){
		batch = new SpriteBatch();
		camera = new OrthographicCamera();
		viewport = new FitViewport(
				Constants.VIEWPORT_WIDTH, 
				Constants.VIEWPORT_HEIGHT,
				camera);
		camera.position.set(Constants.VIEWPORT_WIDTH * 0.5f, Constants.VIEWPORT_HEIGHT * 0.5f, 0); //0,0 on keskellä ruutua
		camera.update();
		cameraGUI = new OrthographicCamera(); //Constants.VIEWPORT_WIDTH, Constants.VIEWPORT_HEIGHT
		viewportGUI = new FitViewport(
				Constants.VIEWPORT_WIDTH_RES, 
				Constants.VIEWPORT_HEIGHT_RES,
				cameraGUI);
		cameraGUI.position.set(0, 0, 0);
		cameraGUI.setToOrtho(false);
		cameraGUI.update();
	}
	
	public void render(){
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		renderWorld(batch);
		batch.end();
		//worldController.level.renderPlayer(camera); //shaperenderer yhdelle vihulle plus pelaajalle
		renderGUI(batch);
	}
	
	public void resize(int width, int height){
		viewport.update(width, height);
		camera.update();
		/*cameraGUI.viewportHeight = Constants.VIEWPORT_HEIGHT_RES;
		cameraGUI.viewportWidth = (Constants.VIEWPORT_HEIGHT_RES
		/ (float)height) * (float)width;
		cameraGUI.position.set(cameraGUI.viewportWidth / 2,
		cameraGUI.viewportHeight / 2, 0);*/
		viewportGUI.update(width, height);
		cameraGUI.update();
	}

	@Override
	public void dispose() {
		batch.dispose();
	}
	
	private void renderGUI(SpriteBatch batch){
		batch.setProjectionMatrix(cameraGUI.combined);
		batch.begin();
		renderFPS(batch);
		renderGuiScore(batch);
		renderEverythingElseGUI(batch);
		if (!worldController.level.playerHasLives()){
			renderEnd(batch);
		} 
		if (worldController.level.gameWon){
			if (worldController.level.gameWon){
				renderWinning(batch);
			}
		}
		batch.end();
	}
	
	private void renderEverythingElseGUI(SpriteBatch batch2) {
		batch2.draw(
				Assets.instance.decorations.backPalkki, 10, Constants.VIEWPORT_WIDTH_RES - 260, 0, 0, 100, 20, 1, 1, 0);
		batch2.draw(
				Assets.instance.decorations.frontPalkki, 10, Constants.VIEWPORT_WIDTH_RES - 260, 0, 0, 100 * worldController.level.getPlayerHeatPros(), 20, 1, 1, 0);
		batch2.draw(
				Assets.instance.decorations.backPalkki, 10, Constants.VIEWPORT_WIDTH_RES - 290, 0, 0, 100, 20, 1, 1, 0);
		batch2.draw(
				Assets.instance.decorations.frontPalkki2, 10, Constants.VIEWPORT_WIDTH_RES - 290, 0, 0, 100 * worldController.level.getPlayerFuelPros(), 20, 1, 1, 0);
	}

	private void renderWinning(SpriteBatch batch2) {
		Assets.instance.fonts.defaultNormal.draw(batch2, "You is best! You has win! Kratz!", Constants.VIEWPORT_WIDTH_RES * 0.2f, 
				Constants.VIEWPORT_HEIGHT_RES * 0.4f);
	}

	private void renderFPS(SpriteBatch batch){
		float y = Constants.VIEWPORT_HEIGHT_RES - 550;
		float x = Constants.VIEWPORT_WIDTH_RES - 130;
		int fps = Gdx.graphics.getFramesPerSecond();
		Assets.instance.fonts.defaultNormal.draw(batch, "FPS: " + fps, x, y);
	}

	private void renderWorld(SpriteBatch batch){
		//if (worldController.level.playerHasLives()){
			batch.draw(
					Assets.instance.decorations.wallpaper, 
					0, 0, 0, 0, 
					Assets.instance.decorations.wallpaper.getRegionWidth(),
					Assets.instance.decorations.wallpaper.getRegionHeight(), 0.1f, 0.1f, 0);
			worldController.level.render(batch);
		//} 
	}
	
	private void renderEnd(SpriteBatch batch){
		Assets.instance.fonts.defaultNormal.draw(batch, "You lose!", Constants.VIEWPORT_WIDTH_RES * 0.5f, 
				Constants.VIEWPORT_HEIGHT_RES * 0.5f);
	}
	
	private void renderGuiScore(SpriteBatch batch){
		Assets.instance.fonts.defaultNormal.draw(batch, "Points: " + worldController.level.getPlayerScore(), 10, Constants.VIEWPORT_HEIGHT_RES);
	}
}
