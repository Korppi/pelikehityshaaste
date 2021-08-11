package com.fatraven.proroglili.screens;

import com.badlogic.gdx.Screen;
import com.fatraven.proroglili.ProRogLiLi;

public abstract class MyScreen implements Screen{
	//private final ProRogLiLi game;
	
	public MyScreen(ProRogLiLi game){
		//this.game = game;
	}
	
	public abstract void render(float deltaTime);
	public abstract void resize(int width, int height);
	public abstract void show();
	public abstract void hide();
	
	public void resume(){}
	public void dispose(){}
	public void pause(){}
}
