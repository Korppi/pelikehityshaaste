package com.fatraven.AstroBlasterClone.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.fatraven.AstroBlasterClone.AstroBlasterMain;
import com.fatraven.AstroBlasterClone.util.Constants;

public class DesktopLauncher {
	public static void main (String[] arg) {
		/*Settings settings = new Settings();
		settings.debug = false;	//poista sit joskus	
		settings.filterMag = TextureFilter.Linear;
		settings.filterMin = TextureFilter.Linear;
		
		TexturePacker.process(
				settings,
				Constants.TEXTURE_ATLAS_SOURCE_FOLDER, 
				Constants.TEXTURE_ATLAS_DESTINATION_FOLDER, 
				Constants.TEXTURE_ATLAS_NAME);
		
		TexturePacker.process(
				settings, 
				Constants.GUI_RAW_TEXTURES, 
				Constants.GUI_DEST_FOLDER, 
				Constants.GUI_ATLAS_NAME);*/
		
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.resizable = false;
		config.fullscreen = false;
		config.width = Constants.VIEWPORT_WIDTH_RES;
		config.height = Constants.VIEWPORT_HEIGHT_RES;
		config.title = Constants.GAME_TITLE;
		config.vSyncEnabled = true;
		new LwjglApplication(new AstroBlasterMain(), config);
	}
}
