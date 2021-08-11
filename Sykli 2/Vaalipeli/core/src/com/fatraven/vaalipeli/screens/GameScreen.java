package com.fatraven.vaalipeli.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.fatraven.vaalipeli.game.WorldController;
import com.fatraven.vaalipeli.game.WorldRenderer;

public class GameScreen extends AbstractScreen{
	public static final String TAG = GameScreen.class.getName();
	
	private WorldController controller;
	private WorldRenderer renderer;

	public GameScreen(Game game) {
		super(game);
	}

	@Override
	public void render(float deltaTime) {
		controller.update(deltaTime);
		Gdx.gl20.glClearColor(1, 1, 1, 1);
		Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
		renderer.render();
	}

	@Override
	public void resize(int width, int height) {
		controller.resize(width, height);
		renderer.resize(width, height);
	}

	@Override
	public void show() {
		controller = new WorldController(game);
		renderer = new WorldRenderer(controller);
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

}
