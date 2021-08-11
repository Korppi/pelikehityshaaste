package com.fatraven.vaalipeli;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.fatraven.vaalipeli.game.Assets;
import com.fatraven.vaalipeli.screens.MenuScreen;

public class Vaalipeli extends Game {
	public static final String TAG = Vaalipeli.class.getName();
	
	@Override
	public void create () {
		Assets.instance.init(new AssetManager());
		setScreen(new MenuScreen(this));
	}
}
