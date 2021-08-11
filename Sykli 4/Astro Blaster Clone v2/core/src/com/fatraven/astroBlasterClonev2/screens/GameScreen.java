package com.fatraven.astroBlasterClonev2.screens;

import com.fatraven.astroBlasterClonev2.AstroBlaster;
import com.fatraven.astroBlasterClonev2.game.Assets;
import com.fatraven.astroBlasterClonev2.game.WorldController;
import com.fatraven.astroBlasterClonev2.game.WorldRenderer;

public class GameScreen extends AbstractScreen{
	//private AstroBlaster game;
	private WorldRenderer renderer;
	private WorldController controller;

	public GameScreen(final AstroBlaster game){
		//this.game = game;
		controller = new WorldController(game);
		renderer = new WorldRenderer(controller);
	}

	@Override
	public void render(float deltaTime) {
		controller.update(deltaTime);
		renderer.render();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		Assets.instance.sounds.theme.play();
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

}
