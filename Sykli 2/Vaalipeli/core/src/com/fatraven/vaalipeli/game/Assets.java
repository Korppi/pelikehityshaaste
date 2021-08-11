package com.fatraven.vaalipeli.game;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Disposable;

public class Assets implements Disposable{
	public static final String TAG = Assets.class.getName();
	public static final Assets instance = new Assets();
	
	private AssetManager manager;
	public Grafs grafs;
	public Sounds sounds;
	
	@Override
	public void dispose() {
		manager.dispose();
	}
	
	public void init(AssetManager manager){
		this.manager = manager;
		this.manager.load("graphics/assets.atlas", TextureAtlas.class);
		this.manager.load("sound/enter.wav", Sound.class);
		this.manager.load("sound/misc_menu_4.wav", Sound.class);
		this.manager.load("sound/positive.wav", Sound.class);
		this.manager.load("sound/negative.wav", Sound.class);
		this.manager.finishLoading();
		TextureAtlas atlas = manager.get("graphics/assets.atlas");
		this.grafs = new Grafs(atlas);
		this.sounds = new Sounds(manager);
	}
	
	public class Sounds{
		public final Sound buttonEnter;
		public final Sound buttonClick;
		public final Sound right;
		public final Sound wrong;
		
		public Sounds(AssetManager mana){
			buttonEnter = mana.get("sound/misc_menu_4.wav", Sound.class);
			buttonClick = mana.get("sound/enter.wav", Sound.class);
			right = mana.get("sound/positive.wav", Sound.class);
			wrong = mana.get("sound/negative.wav", Sound.class);
		}
	}
	
	public class Grafs{
		public final TextureRegion house;
		public final TextureRegion table;
		public final TextureRegion votingHouse;
		public final TextureRegion votingBox;
		
		public Grafs(TextureAtlas atlas){
			house = atlas.findRegion("taka-ala");
			table = atlas.findRegion("tyopoyta");
			votingBox = atlas.findRegion("arkku");
			votingHouse = atlas.findRegion("koppi");
		}
	}
}
