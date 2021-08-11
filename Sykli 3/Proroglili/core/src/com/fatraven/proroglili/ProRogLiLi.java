package com.fatraven.proroglili;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.fatraven.proroglili.game.Assets;
import com.fatraven.proroglili.game.RandomGen;
import com.fatraven.proroglili.screens.CreditScreen;
import com.fatraven.proroglili.screens.GameScreen;
import com.fatraven.proroglili.screens.MenuScreen;

public class ProRogLiLi extends Game{
	public MenuScreen menuScreen;
	public CreditScreen creditScreen;
	public GameScreen gameScreen;
	
	@Override
	public void create () {
		long startTime = System.currentTimeMillis();
		RandomGen.instance.init(1);
		Assets.instance.init(new AssetManager());
		menuScreen = new MenuScreen(this);
		creditScreen = new CreditScreen(this);
		gameScreen = new GameScreen(this);
		setScreen(menuScreen);
		long endTime = System.currentTimeMillis();
		System.out.println("Milliseconds:" + String.valueOf(endTime - startTime));
	}
}
