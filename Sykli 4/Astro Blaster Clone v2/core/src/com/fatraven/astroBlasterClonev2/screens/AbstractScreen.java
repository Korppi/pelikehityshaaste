package com.fatraven.astroBlasterClonev2.screens;

import com.badlogic.gdx.Screen;

public abstract class AbstractScreen implements Screen{
	
	public AbstractScreen(){
	}
	
	public abstract void render(float deltaTime);
	public abstract void resize(int width, int height);
	public abstract void show();
	public abstract void hide();
	public abstract void pause();
	
	public void resume(){
	}
	
	public void dispose(){
		//Assets.instance.dispose();
	}
}
