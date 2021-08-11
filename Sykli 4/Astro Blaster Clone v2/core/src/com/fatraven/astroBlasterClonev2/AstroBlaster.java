package com.fatraven.astroBlasterClonev2;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.utils.Array;
import com.fatraven.astroBlasterClonev2.game.Assets;
import com.fatraven.astroBlasterClonev2.screens.AbstractScreen;
import com.fatraven.astroBlasterClonev2.screens.CreditScreen;
import com.fatraven.astroBlasterClonev2.screens.GameScreen;
import com.fatraven.astroBlasterClonev2.screens.MenuScreen;
import com.fatraven.astroBlasterClonev2.screens.ScreenType;

public class AstroBlaster extends Game {
	private Array<AbstractScreen> screens;
	int calc = 0;
	
	@Override
	public void create () {
		Assets.instance.init(new AssetManager());
		screens = new Array<AbstractScreen>();
		screens.add(new MenuScreen(this));
		screens.add(new CreditScreen(this));
		screens.add(new GameScreen(this));
		setScreenByType(ScreenType.MENUSCREEN);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		getScreen().render(Gdx.graphics.getDeltaTime());
	}
	
	public void setScreenByType(ScreenType type){
		setScreen(screens.get(type.getValue()));
	}
}
