package com.fatraven.proroglili.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.fatraven.proroglili.ProRogLiLi;
//ihan vaan testi
public class DesktopLauncher {
	public static void main (String[] arg) {/*
		Settings settings = new Settings
		();
		settings.maxHeight = 2048;
		settings.maxWidth = 2048;
		settings.filterMin = TextureFilter.Nearest;
		settings.filterMag = TextureFilter.Nearest;
		
		TexturePacker.process(
				settings, 
				"F:/Pelisisältö/grafiikka/ProRogLiLi", 
				"../core/assets/graphics/", 
				"proroglili_graphs");*/
		
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.resizable = true;
		config.title = "ProRogLili";
		config.width = 800;
		config.height = 600;
		new LwjglApplication(new ProRogLiLi(), config);
	}
}
