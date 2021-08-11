package com.fatraven.proroglili.game;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Disposable;

public class Assets implements Disposable{
	public static final String TAG = Assets.class.getName();
	public static final Assets instance = new Assets();
	
	private AssetManager assetManager;
	
	public Misc misc;
	public Graphics graphics;
	public MouseSounds mouseSounds;
	public Musics musics;
	
	private Assets(){};
	
	public void init(AssetManager assetManager){
		this.assetManager = assetManager;
		assetManager.load("GUI/gui.json", Skin.class);
		assetManager.load("graphics/proroglili_graphs.atlas", TextureAtlas.class);
		assetManager.load("sounds/mouse_click.wav", Sound.class);
		assetManager.load("sounds/mouse_out.wav", Sound.class);
		assetManager.load("sounds/mouse_over.wav", Sound.class);
		assetManager.load("sounds/trance-menu.mp3", Music.class);
		assetManager.finishLoading();
		misc = new Misc(assetManager);
		TextureAtlas atlas = assetManager.get("graphics/proroglili_graphs.atlas");
		graphics = new Graphics(atlas);
		mouseSounds = new MouseSounds(assetManager);
		musics = new Musics(assetManager);
	}
	
	public class Graphics{
		public final TextureRegion backgroundMenu;
		public final TextureRegion logo;
		public final TextureRegion barrel;
		public final TextureRegion knightSmall;
		public final TextureRegion knightBig;
		public final TextureRegion fireLeft;
		public final TextureRegion fireRight;
		public final TextureRegion door;
		public final TextureRegion wallStoneGrass;
		public final TextureRegion wallStone;
		public final TextureRegion floorWoodLight;
		
		public Graphics(TextureAtlas atlas){
			backgroundMenu = atlas.findRegion("bkg");
			logo = atlas.findRegion("logo");
			barrel = atlas.findRegion("graphs/barrel");
			knightSmall = atlas.findRegion("graphs/knight_small");
			knightBig = atlas.findRegion("graphs/knight_big");
			fireLeft = atlas.findRegion("graphs/fire_left");
			fireRight = atlas.findRegion("graphs/fire_right");
			door = atlas.findRegion("graphs/door");
			wallStone = atlas.findRegion("graphs/wall_stone_dark2");
			wallStoneGrass = atlas.findRegion("graphs/wall_stone_dark");
			floorWoodLight = atlas.findRegion("graphs/floor_wood_light");
		}
	}
	
	public class Misc{
		public final Skin skin;
		
		public Misc(AssetManager assetManager){
			skin = assetManager.get("GUI/gui.json", Skin.class);
		}
	}
	
	public class Musics{
		public final Music menu;
		
		public Musics(AssetManager assetManager){
			menu = assetManager.get("sounds/trance-menu.mp3");
		}
	}
	
	public class MouseSounds{
		public final Sound click;
		public final Sound out;
		public final Sound over;
		
		public MouseSounds(AssetManager assetManager){
			click = assetManager.get("sounds/mouse_click.wav");
			out = assetManager.get("sounds/mouse_out.wav");
			over = assetManager.get("sounds/mouse_over.wav");
		}
	}

	@Override
	public void dispose() {
		assetManager.dispose();
	}
}
