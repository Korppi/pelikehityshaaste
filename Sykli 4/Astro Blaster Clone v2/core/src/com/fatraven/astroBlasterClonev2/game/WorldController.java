package com.fatraven.astroBlasterClonev2.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fatraven.astroBlasterClonev2.AstroBlaster;
import com.fatraven.astroBlasterClonev2.game.levels.Level;
import com.fatraven.astroBlasterClonev2.game.levels.SquadronAsteroid;
import com.fatraven.astroBlasterClonev2.game.levels.SquadronOne;
import com.fatraven.astroBlasterClonev2.game.levels.SquadronThree;
import com.fatraven.astroBlasterClonev2.game.levels.SquadronTwo;
import com.fatraven.astroBlasterClonev2.screens.GameScreen;

public class WorldController {
	private Level currentLevel;
	private AstroBlaster game;
	private boolean paused;
	
	public WorldController(AstroBlaster game){
		this.game = game;
		paused = false;
		currentLevel = new Level(game);
	}
	
	public void update(float deltaTime){
		if (Gdx.input.isKeyJustPressed(Keys.SHIFT_LEFT)) paused = !paused;
		if (Gdx.input.isKeyJustPressed(Keys.ESCAPE)){
			Gdx.app.exit();
		} else if (Gdx.input.isKeyJustPressed(Keys.ENTER)){
			SquadronOne.levely = 0;
			SquadronTwo.levely = 0;
			SquadronThree.levely = 0;
			SquadronAsteroid.levely = 0;
			game.setScreen(new GameScreen(game));
		}
		if (!paused) currentLevel.update(deltaTime);
	}

	public void draw(SpriteBatch batch) {
		currentLevel.draw(batch);
	}
	
	public int getPoints(){
		return currentLevel.getPlayerPoints();
	}

	public float getHeatPros() {
		return currentLevel.getHeatPros();
	}

	public float getFuelPros() {
		return currentLevel.getFuelPros();
	}

	public boolean playerLost() {
		return currentLevel.levelLost();
	}

	public boolean playerWon() {
		return currentLevel.isGameWon();
	}
}
