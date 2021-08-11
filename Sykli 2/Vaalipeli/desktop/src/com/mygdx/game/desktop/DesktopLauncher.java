package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;/*
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.tools.texturepacker.TexturePacker;
import com.badlogic.gdx.tools.texturepacker.TexturePacker.Settings;*/
import com.fatraven.vaalipeli.Vaalipeli;

public class DesktopLauncher {
	public static void main (String[] arg) {/*
		Settings settings = new Settings();
		settings.filterMin = TextureFilter.Linear;
		settings.filterMag = TextureFilter.Linear;
		TexturePacker.process(
				settings, 
				"C:/Users/Marsu/Desktop/Pelikehityshaaste/Sykli 2 - Politiikkapeli/grafiikat", 
				"assets/graphics/",
				"assets");*/
		
		
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 800;
		config.height = 600;
		config.resizable = false;
		config.title = "Äänestystilaisuus";
		new LwjglApplication(new Vaalipeli(), config);
	}
}
