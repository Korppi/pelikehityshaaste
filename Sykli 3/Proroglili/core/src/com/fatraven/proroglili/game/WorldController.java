package com.fatraven.proroglili.game;

import com.badlogic.gdx.Game;
import com.fatraven.proroglili.game.dungeon.Level;

public class WorldController {
	public Level level;
	//private Game game;
	
	public WorldController(Game game){
		//this.game = game;
		init();
	}
	
	private void init() {
		level = new Level();
		level.init();
	}

	public void update(float deltaTime){
		level.update(deltaTime);
	}
}
