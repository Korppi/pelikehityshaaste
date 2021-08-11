package com.fatraven.AstroBlasterClone.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class GamePreferences {
	public static final String TAG = GamePreferences.class.getName();
	
	public static final GamePreferences instance = new GamePreferences();
	
	public int highscore;
	
	private Preferences prefs;
	
	private GamePreferences(){
		prefs = Gdx.app.getPreferences(Constants.PREFERENCES);
	}

	public void load(){
		highscore = prefs.getInteger("highscore", 0);
	}
	
	public void save(){
		prefs.putInteger("highscore", highscore);
		prefs.flush();
	}
}
