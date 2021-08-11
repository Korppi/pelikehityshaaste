package com.fatraven.vaalipeli.game;

import com.badlogic.gdx.Game;

public class WorldController {
	public static final String TAG = WorldController.class.getName();
	
	private Game game;
	private Level level;
	
	public WorldController(Game game){
		this.game = game;
		init();
	}
	
	private void init(){
		level = new Level(game);
	}
	
	public void update(float deltaTime){
		level.update(deltaTime);
	}
	
	public void resize(int width, int height){
		level.resize(width, height);
	}
	
	public Level getLevel() {
		return level;
	}
}
