package com.fatraven.AstroBlasterClone;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.fatraven.AstroBlasterClone.game.Assets;
import com.fatraven.AstroBlasterClone.screens.MenuScreen;

public class AstroBlasterMain extends Game {

	@Override
	public void create() {
		Gdx.input.setCursorCatched(true);
		Gdx.app.setLogLevel(Application.LOG_DEBUG);
		Assets.instance.init(new AssetManager());
		setScreen(new MenuScreen(this));
	}
}
