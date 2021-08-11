package com.fatraven.vaalipeli.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;

public abstract class AbstractScreen implements Screen{
	protected Game game;
	
	public AbstractScreen(Game game){
		this.game = game;
	}
	
	public abstract void render(float deltaTime);
	public abstract void resize(int width, int height);
	public abstract void show();
	public abstract void hide();
	
	//turhia, koska ei mobiili
	public void resume(){}
	public void dispose(){}
	public void pause(){}
}
