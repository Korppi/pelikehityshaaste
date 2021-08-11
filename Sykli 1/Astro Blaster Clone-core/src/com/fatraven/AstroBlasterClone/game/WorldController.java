package com.fatraven.AstroBlasterClone.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.fatraven.AstroBlasterClone.screens.MenuScreen;

public class WorldController {
	public static final String TAG = WorldController.class.getName();
	public Level level;
	private Game game;
	private float timer;
	
	public WorldController(Game game){
		this.game = game;
		init();
	}
	
	public void init(){
		level = new Level();
		timer = 2.5f;
	}
	
	public void update(float deltaTime){
		if (Gdx.input.isKeyJustPressed(Keys.ESCAPE)) Gdx.app.exit();
		if (level.playerHasLives()){
			if (!level.gameWon){
				level.update(deltaTime);
			} else {
				timer -= deltaTime;
				if (timer <= 0) backToMenu();
			}
		} else {
			timer -= deltaTime;
			if (timer <= 0) backToMenu();
		}
	}
	
	private void backToMenu(){
		game.setScreen(new MenuScreen(game));
	}
}
