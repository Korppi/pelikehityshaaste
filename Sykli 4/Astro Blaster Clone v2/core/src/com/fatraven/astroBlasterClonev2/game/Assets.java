package com.fatraven.astroBlasterClonev2.game;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Array;

public class Assets {
	public static final Assets instance = new Assets(); //singleton
	
	private AssetManager assetManager;
	public Misc misc;
	public GUI gui;
	public Sounds sounds;
	public ShipsAndShit shipsAndShit;
	
	private Assets(){}; //tyhjä konstruktori
	
	public void init(AssetManager assetManager){
		this.assetManager = assetManager;
		assetManager.load("GUI/gui.json", Skin.class);
		assetManager.load("images/astro_blaster_clone_graphs.atlas", TextureAtlas.class);
		assetManager.load("images/logo1.png", Texture.class);
		assetManager.load("sounds/Laser_Shoot5.wav", Sound.class);
		assetManager.load("sounds/Hit_Hurt6.wav", Sound.class);
		assetManager.load("sounds/Explosion6.wav", Sound.class);
		assetManager.load("sounds/Powerup3.wav", Sound.class);
		assetManager.load("sounds/Randomize11.wav", Sound.class);
		assetManager.load("sounds/select.wav", Sound.class);
		assetManager.load("sounds/theme.mp3", Music.class);
		assetManager.finishLoading();
		gui = new GUI(this.assetManager);
		TextureAtlas atlas = this.assetManager.get("images/astro_blaster_clone_graphs.atlas");
		misc = new Misc(atlas, this.assetManager);
		sounds = new Sounds(this.assetManager);
		shipsAndShit = new ShipsAndShit(atlas);
	}
	
	public class ShipsAndShit{
		public final TextureRegion beetle;
		public final TextureRegion beam;
		public final TextureRegion turret;
		public final TextureRegion bullet;
		public final TextureRegion player;
		public final TextureRegion shield;
		
		public ShipsAndShit(TextureAtlas atlas){
			beetle = atlas.findRegion("ships and shit/Beetle");;
			beam = atlas.findRegion("ships and shit/BeamMk2");
			turret = atlas.findRegion("ships and shit/Turret");
			bullet = atlas.findRegion("ships and shit/bullet");
			player = atlas.findRegion("ships and shit/redfighter0005");
			shield = atlas.findRegion("ships and shit/spr_shield");
			beam.flip(false, true);
			turret.flip(false, true);
			beetle.flip(false, true);
		}
	}
	
	public class Misc{
		public final TextureRegion background;
		public final Texture logo;
		public final Array<TextureRegion> explosions;
		public final Array<TextureRegion> brownAsteroids;
		public final Array<TextureRegion> powerUp;
		public final Array<TextureRegion> greyAsteroids;
		public final Array<TextureRegion> redAsteroids;
		
		public Misc(TextureAtlas atlas, AssetManager manager){
			background = atlas.findRegion("408-0");
			logo = manager.get("images/logo1.png", Texture.class);
			explosions = new Array<TextureRegion>();
			powerUp = new Array<TextureRegion>();
			for (int i = 0; i < 32; i++){
				//expl00a - expl31a
				explosions.add(atlas.findRegion(String.format("ships and shit/expl%02da", i)));
				if (i >= 0 && i < 2){
					powerUp.add(atlas.findRegion(String.format("ships and shit/expl%02da", i)));
				}
			}
			brownAsteroids = new Array<TextureRegion>();
			greyAsteroids = new Array<TextureRegion>();
			redAsteroids = new Array<TextureRegion>();
			for (int i = 0; i < 16; i++){
				brownAsteroids.add(atlas.findRegion(String.format("ships and shit/a100%02d", i)));
				greyAsteroids.add(atlas.findRegion(String.format("ships and shit/a300%02d", i)));
				redAsteroids.add(atlas.findRegion(String.format("ships and shit/a400%02d", i)));
			}
		}
	}
	
	public class GUI{
		public final Skin skin;
		public final BitmapFont font;
		public final TextureRegion backPalkki;
		public final TextureRegion frontPalkki1;
		public final TextureRegion frontPalkki2;
		public GUI(AssetManager manager){
			skin = manager.get("GUI/gui.json", Skin.class);
			font = skin.getFont("medium");
			TextureAtlas a = manager.get("images/astro_blaster_clone_graphs.atlas");
			backPalkki = a.findRegion("ships and shit/backPalkki");
			frontPalkki1 = a.findRegion("ships and shit/frontPalkki");
			frontPalkki2 = a.findRegion("ships and shit/frontPalkki2");
		}
	}
	
	public class Sounds{
		public final Sound select;
		public final Sound fireLaser;
		public final Sound hit;
		public final Sound explosion;
		public final Sound pup;
		public final Sound spawn;
		public final Music theme;
		public Sounds(AssetManager manager){
			select = manager.get("sounds/select.wav", Sound.class);
			fireLaser = manager.get("sounds/Laser_Shoot5.wav", Sound.class);
			hit = manager.get("sounds/Hit_Hurt6.wav", Sound.class);
			explosion = manager.get("sounds/Explosion6.wav", Sound.class);
			pup = manager.get("sounds/Powerup3.wav", Sound.class);
			spawn = manager.get("sounds/Randomize11.wav", Sound.class);
			theme = manager.get("sounds/theme.mp3", Music.class);
			theme.setLooping(true);
		}
	}
	
	public class Musics{
		public final Music music;
		public Musics(AssetManager manager){
			music = manager.get("sounds/theme.mp3", Music.class);
		}
	}
}
