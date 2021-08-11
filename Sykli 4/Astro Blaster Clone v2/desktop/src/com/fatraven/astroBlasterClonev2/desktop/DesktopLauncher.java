package com.fatraven.astroBlasterClonev2.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.fatraven.astroBlasterClonev2.AstroBlaster;

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
						"source", 
						"kohde (vissiin ../core/)", 
						"abc");*/
		
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.resizable = false;
		config.fullscreen = false;
		config.width = 800;
		config.height = 600;
		config.title = "ABC V2";
		new LwjglApplication(new AstroBlaster(), config);
	}
}
