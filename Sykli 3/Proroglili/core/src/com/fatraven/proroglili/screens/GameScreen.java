package com.fatraven.proroglili.screens;

import com.fatraven.proroglili.ProRogLiLi;
import com.fatraven.proroglili.game.WorldController;
import com.fatraven.proroglili.game.WorldRenderer;

public class GameScreen extends MyScreen{
	private WorldController worldController;
	private WorldRenderer worldRenderer;

	public GameScreen(ProRogLiLi game) {
		super(game);
		worldController = new WorldController(game);
		worldRenderer = new WorldRenderer(worldController);
	}

	@Override
	public void render(float deltaTime) {
		worldController.update(deltaTime);
		worldRenderer.render();
	}

	@Override
	public void resize(int width, int height) {
		worldRenderer.resize(width, height);
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

}
