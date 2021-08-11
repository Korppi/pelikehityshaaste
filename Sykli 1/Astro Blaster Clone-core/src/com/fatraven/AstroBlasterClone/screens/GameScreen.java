package com.fatraven.AstroBlasterClone.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.fatraven.AstroBlasterClone.game.Assets;
import com.fatraven.AstroBlasterClone.game.WorldController;
import com.fatraven.AstroBlasterClone.game.WorldRenderer;

public class GameScreen extends AbstractScreen{
	
	private WorldController worldController;
	private WorldRenderer worldRenderer;

	public GameScreen(Game game) {
		super(game);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void render(float deltaTime) {
		worldController.update(deltaTime);
		Gdx.gl20.glClearColor(1, 1, 1, 1);
		Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
		worldRenderer.render();
	}

	@Override
	public void resize(int width, int height) {
		worldRenderer.resize(width, height);
	}

	@Override
	public void show() {
		worldController = new WorldController(game);
		worldRenderer = new WorldRenderer(worldController);
		Assets.instance.music.song.setVolume(0.75f);
		Assets.instance.music.song.setLooping(true);
		Assets.instance.music.song.play();
	}

	@Override
	public void hide() {
		//worldRenderer.dispose();
		Assets.instance.music.song.stop();
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

}
