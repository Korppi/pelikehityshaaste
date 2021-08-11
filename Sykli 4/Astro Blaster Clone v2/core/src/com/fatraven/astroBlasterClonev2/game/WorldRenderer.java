package com.fatraven.astroBlasterClonev2.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class WorldRenderer {
	private SpriteBatch batch;
	private WorldController controller;
	
	public WorldRenderer(WorldController controller){
		this.controller = controller;
		batch = new SpriteBatch();
	}
	
	public void render(){
		batch.begin();
		drawWorld();
		drawPoints();
		drawThings();
		if (!controller.playerLost()){
			drawEndingScore("You lose!");
		} else if (controller.playerWon()){
			drawEndingScore("You won!");
		}
		drawFps();
		batch.end();
	}

	private void drawFps() {
		Assets.instance.gui.font.draw(batch, "FPS: " + Gdx.graphics.getFramesPerSecond(), 720, 20);
	}

	private void drawEndingScore(String text) {
		Assets.instance.gui.font.draw(batch, text + " You got " + controller.getPoints() + " points!" , 300, 300);
		Assets.instance.gui.font.draw(batch, "Enter: new game" , 300, 250);
		Assets.instance.gui.font.draw(batch, "Esc: quit game" , 300, 200);
	}

	private void drawThings() {
		batch.draw(Assets.instance.gui.backPalkki, 20, 530, 0, 0, 100, 20, 1, 1, 0);
		batch.draw(Assets.instance.gui.frontPalkki1, 20, 530, 0, 0, 100 * controller.getHeatPros(), 20, 1, 1, 0);
		batch.draw(Assets.instance.gui.backPalkki, 20, 500, 0, 0, 100, 20, 1, 1, 0);
		batch.draw(Assets.instance.gui.frontPalkki2, 20, 500, 0, 0, 100 * controller.getFuelPros(), 20, 1, 1, 0);
	}

	private void drawPoints() {
		Assets.instance.gui.font.draw(batch, "Points: " + controller.getPoints(), 20, 580);
	}

	private void drawWorld() {
		batch.draw(Assets.instance.misc.background, 0, 0);
		controller.draw(batch);
	}
}
