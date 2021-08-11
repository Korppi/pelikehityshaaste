package com.fatraven.AstroBlasterClone.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetErrorListener;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.BitmapFontLoader.BitmapFontParameter;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import com.fatraven.AstroBlasterClone.util.Constants;

public class Assets implements Disposable, AssetErrorListener{
	public static final String TAG = Assets.class.getName();
	public static final Assets instance = new Assets();
	
	public AssetAsteroids asteroids;
	public AssetDecorations decorations;
	public AssetShipsAndShit shipsAndShit;
	public AssetFonts fonts;
	public AssetSounds sounds;
	public AssetMusic music;
	public AssetExplosion explosions;
	
	private AssetManager assetManager;
	
	private Assets() {} 
	//hyv‰ tapa olla konstruktori mutta private ettei kukaan muu voi tehd‰ instanssia, kuin t‰‰ luokka
	//Singleton = eli vain yksi instanssi on mahdollista luoda
	
	public void init (AssetManager assetManager){
		this.assetManager = assetManager;
		assetManager.setErrorListener(this);
		assetManager.load(Constants.TEXTURE_ATLAS, TextureAtlas.class);
		BitmapFontParameter param = new BitmapFontParameter(); //oletukset riitt‰‰ joten n‰ill‰ menn‰‰n
		assetManager.load(Constants.FONT, BitmapFont.class, param);
		assetManager.load("sounds/select.wav", Sound.class);
		assetManager.load("sounds/Explosion6.wav", Sound.class);
		assetManager.load("sounds/Hit_Hurt6.wav", Sound.class);
		assetManager.load("sounds/Laser_Shoot5.wav", Sound.class);
		assetManager.load("sounds/Powerup3.wav", Sound.class);
		assetManager.load("sounds/Randomize11.wav", Sound.class);
		assetManager.load("sounds/theme.mp3", Music.class);
		assetManager.finishLoading();
		//Gdx.app.debug(TAG, "Assets loaded: " + assetManager.getAssetNames().size);
		for (String name : assetManager.getAssetNames()) Gdx.app.debug(TAG, "Asset: " + name);
		
		TextureAtlas atlas = assetManager.get(Constants.TEXTURE_ATLAS);
		
		//Gdx.app.debug(TAG, "atlas regions: " + atlas.getRegions().size);
		
		asteroids = new AssetAsteroids(atlas);
		decorations = new AssetDecorations(atlas);
		shipsAndShit = new AssetShipsAndShit(atlas);
		explosions = new AssetExplosion(atlas);
		fonts = new AssetFonts(assetManager.get(Constants.FONT, BitmapFont.class));
		sounds = new AssetSounds(assetManager);
		music = new AssetMusic(assetManager);
		
	}

	@Override
	public void dispose() {
		assetManager.dispose();
	}

	@SuppressWarnings("rawtypes") //tuo AssetDescriptori heitt‰‰ turhaa varoitusta
	@Override
	public void error(AssetDescriptor asset, Throwable throwable) {
		Gdx.app.error(TAG, "Error while loading asset: " + asset.fileName.toString(), throwable);
	}
	
	public class AssetShipsAndShit{
		public Array<AtlasRegion> redship;
		public final AtlasRegion shield;
		public final AtlasRegion bullet;
		public final AtlasRegion enemyOne;
		public final AtlasRegion enemyTwo;
		public final AtlasRegion enemyThree;
		
		public AssetShipsAndShit(TextureAtlas atlas){
			redship = new Array<TextureAtlas.AtlasRegion>();
			redship.add(atlas.findRegion("ships and shit/redfighter0001"));
			redship.add(atlas.findRegion("ships and shit/redfighter0002"));
			redship.add(atlas.findRegion("ships and shit/redfighter0003"));
			redship.add(atlas.findRegion("ships and shit/redfighter0004"));
			redship.add(atlas.findRegion("ships and shit/redfighter0005"));
			redship.add(atlas.findRegion("ships and shit/redfighter0006"));
			redship.add(atlas.findRegion("ships and shit/redfighter0007"));
			redship.add(atlas.findRegion("ships and shit/redfighter0008"));
			redship.add(atlas.findRegion("ships and shit/redfighter0009"));
			shield = atlas.findRegion("ships and shit/spr_shield");	
			bullet = atlas.findRegion("ships and shit/bullet");
			enemyOne = atlas.findRegion("ships and shit/BeamMk2");
			enemyOne.flip(false, true);
			enemyTwo = atlas.findRegion("ships and shit/Turret");
			enemyTwo.flip(false, true);
			enemyThree = atlas.findRegion("ships and shit/Beetle");
			enemyThree.flip(false, true);
		}
	}
	
	public class AssetAsteroids{
		public Array<AtlasRegion> greyAsteroids;
		public Array<AtlasRegion> redAsteroids;
		public Array<AtlasRegion> brownAsteroids;
		
		public AssetAsteroids(TextureAtlas atlas){
			greyAsteroids = new Array<TextureAtlas.AtlasRegion>();
			redAsteroids = new Array<TextureAtlas.AtlasRegion>();
			brownAsteroids = new Array<TextureAtlas.AtlasRegion>();
			
			int asteroidsCount = 16;
			for (int i = 0; i < asteroidsCount; i++){
				greyAsteroids.add(atlas.findRegion("ships and shit/a100" + String.format("%02d", i)));
				redAsteroids.add(atlas.findRegion("ships and shit/a300" + String.format("%02d", i)));
				brownAsteroids.add(atlas.findRegion("ships and shit/a400" + String.format("%02d", i)));
			}			
		}
	}
	
	public class AssetExplosion{
		public Array<AtlasRegion> explosions;
		
		public AssetExplosion(TextureAtlas atlas){
			int count = 32;
			explosions = new Array<TextureAtlas.AtlasRegion>();
			for (int i = 0; i < count; i++){
				explosions.add(atlas.findRegion("ships and shit/expl" + String.format("%02d", i) + "a"));
			}
		}
	}
	
	public class AssetDecorations{
		public final AtlasRegion wallpaper;
		public final AtlasRegion star;
		public final AtlasRegion frontPalkki;
		public final AtlasRegion backPalkki;
		public final AtlasRegion frontPalkki2;
		
		public AssetDecorations(TextureAtlas atlas){
			wallpaper = atlas.findRegion("408-0");
			star = atlas.findRegion("ships and shit/star");
			frontPalkki = atlas.findRegion("ships and shit/frontPalkki");
			backPalkki = atlas.findRegion("ships and shit/backPalkki");
			frontPalkki2 = atlas.findRegion("ships and shit/frontPalkki2");
		}
	}
	
	public class AssetFonts{
		public final BitmapFont defaultNormal;
		public final BitmapFont defaultBig;
		
		public AssetFonts(BitmapFont font){
			defaultNormal = font;
			defaultBig = font;
			defaultBig.setScale(1.5f);
		}
	}
	
	public class AssetSounds{
		public final Sound select;
		public final Sound explosion;
		public final Sound hurt;
		public final Sound laser;
		public final Sound powerup;
		public final Sound shield;
		
		public AssetSounds(AssetManager manager){
			select = manager.get("sounds/select.wav", Sound.class);
			explosion = manager.get("sounds/Explosion6.wav", Sound.class);
			hurt = manager.get("sounds/Hit_Hurt6.wav", Sound.class);
			powerup = manager.get("sounds/Powerup3.wav", Sound.class);
			shield = manager.get("sounds/Randomize11.wav", Sound.class);
			laser = manager.get("sounds/Laser_Shoot5.wav", Sound.class);
		}
	}
	
	public class AssetMusic {
		public final Music song;
		
		public AssetMusic (AssetManager manager) {
		song = manager.get("sounds/theme.mp3", Music.class);
		}
	}
}
