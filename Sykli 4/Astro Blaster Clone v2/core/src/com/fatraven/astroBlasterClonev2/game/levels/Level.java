package com.fatraven.astroBlasterClonev2.game.levels;

import java.util.Random;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;
import com.fatraven.astroBlasterClonev2.AstroBlaster;
import com.fatraven.astroBlasterClonev2.game.Assets;
import com.fatraven.astroBlasterClonev2.game.gameObjects.Bullet;
import com.fatraven.astroBlasterClonev2.game.gameObjects.Explosion;
import com.fatraven.astroBlasterClonev2.game.gameObjects.GameObject;
import com.fatraven.astroBlasterClonev2.game.gameObjects.Player;
import com.fatraven.astroBlasterClonev2.game.gameObjects.PowerUp;

public class Level {
	private final Level level = this;
	private Array<Squadron> squadrons;
	private Squadron currentSquadron;
	private int levelNumber;
	//private boolean levelCleared;
	private int squadronNumber;
	//private AstroBlaster game;
	private int powerupchange;
	private Player player;
	private final Array<Bullet> activePlayerBullets = new Array<Bullet>();
	private final Array<Bullet> activeEnemyBullets = new Array<Bullet>();
	private final Pool<Bullet> bullets = new Pool<Bullet>(){
		@Override
		protected Bullet newObject(){
			return new Bullet(Assets.instance.shipsAndShit.bullet, level);
		}
	};
	private final Array<Explosion> activeExplosions = new Array<Explosion>();
	private final Pool<Explosion> explosions = new Pool<Explosion>(){
		@Override
		protected Explosion newObject(){
			Animation animation = new Animation(0.02f, Assets.instance.misc.explosions, PlayMode.NORMAL);
			return new Explosion(animation, level);
		}
	};
	private final Array<PowerUp> activePowerups = new Array<PowerUp>();
	private final Pool<PowerUp> powerUps = new Pool<PowerUp>(){
		@Override
		protected PowerUp newObject(){
			Animation animation = new Animation(0.1f, Assets.instance.misc.powerUp, PlayMode.LOOP_PINGPONG);
			PowerUp up = new PowerUp(animation, level);
			int style = ra.nextInt(20) + 1;
			//System.out.println(style);
			up.powerUpStyle = style;
			up.alive = true;
			return up;
		}
	};
	private float slowerTimer;
	private boolean slowerEnabled;
	public final Random ra = new Random();
	private boolean gameWon;
	public String upText;
	public float upTextTimer;
	
	public Level(AstroBlaster game){
		//levelCleared = false;
		upTextTimer = 0;
		upText = "";
		powerupchange = 5;
		gameWon = false;
		//this.game = game;
		player = new Player(Assets.instance.shipsAndShit.player, this);
		levelNumber = 1;
		squadronNumber = 0;
		slowerTimer = 20;
		slowerEnabled = false;
		loadNextLevel(levelNumber);
	}
		
	private void init(int i) {
		squadrons = new Array<Squadron>();
		switch (i) {
		case 1:
			//HUOM!! x ja y on pakko erota edellisestä koordinaatin x ja y arvoista! edes pikselillä!
			squadrons.add(new SquadronOne(level));
			squadrons.get(0).addPoint(new Vector2(5, 150));
			squadrons.get(0).addPoint(new Vector2(391, 152));
			squadrons.get(0).addPoint(new Vector2(200, 250));
			squadrons.get(0).addPoint(new Vector2(5, 180));
			squadrons.get(0).addPoint(new Vector2(100, 300));
			squadrons.get(0).addPoint(new Vector2(320, 350));
			squadrons.get(0).addPoint(new Vector2(50, 351));
			squadrons.get(0).addPoint(new Vector2(5, 400));
			squadrons.get(0).addPoint(new Vector2(400, 401));
			squadrons.get(0).addPoint(new Vector2(160, 402));
			squadrons.get(0).addPoint(new Vector2(350, 403));
			squadrons.get(0).addPoint(new Vector2(392, 153));
			
			squadrons.add(new SquadronTwo(level));
			squadrons.add(new SquadronThree(level));
			squadrons.add(new SquadronAsteroid(level));
			squadrons.add(new SquadronEmptyTank(level, player));
			squadrons.add(new SquadronFillTank(level, player));
			upText = "Ship loaded with 1 slowing weapon!";
			upTextTimer = 5;
			break;
		case 2:
			powerupchange = 15;
			player.slowerUnused = true;
			slowerTimer = 20;
			slowerEnabled = false;
			squadrons.clear();
			squadrons.add(new SquadronOne(level));
			squadrons.get(0).addPoint(new Vector2(5, 150));
			squadrons.get(0).addPoint(new Vector2(291, 152));
			squadrons.get(0).addPoint(new Vector2(200, 250));
			squadrons.get(0).addPoint(new Vector2(5, 180));
			squadrons.get(0).addPoint(new Vector2(100, 300));
			squadrons.get(0).addPoint(new Vector2(220, 350));
			squadrons.get(0).addPoint(new Vector2(50, 351));
			squadrons.get(0).addPoint(new Vector2(5, 400));
			squadrons.get(0).addPoint(new Vector2(200, 401));
			squadrons.get(0).addPoint(new Vector2(160, 402));
			squadrons.get(0).addPoint(new Vector2(250, 403));
			squadrons.get(0).addPoint(new Vector2(292, 153));
			
			squadrons.add(new SquadronTwo(level));
			squadrons.add(new SquadronThree(level));
			squadrons.add(new SquadronAsteroid(level));
			squadrons.add(new SquadronEmptyTank(level, player));
			squadrons.add(new SquadronFillTank(level, player));
			upText = "Ship loaded with 1 slowing weapon!";
			upTextTimer = 5;
			break;
		case 3:
			gameWon = true;
			squadrons.clear();
			break;
		default:
			break;
		}
		if (!gameWon) currentSquadron = squadrons.get(squadronNumber);
	}

	public void loadNextSquadron(){
		squadronNumber++;
		/*System.out.println("squadron numberi lisäyksen jälkeen: " + squadronNumber);
		System.out.println("Squadron listan koko: " + squadrons.size);
		System.out.println("Leveli: " + levelNumber);*/
		if (squadronNumber >= squadrons.size) loadNextLevel(++levelNumber);
		else{
			currentSquadron = squadrons.get(squadronNumber);
			if (currentSquadron instanceof SquadronEmptyTank){
				//System.out.println("empty tank");
				player.skipGasChecker = true;
				upText = "Fuel to points!";
				upTextTimer = 5;
			}
			if (currentSquadron instanceof SquadronFillTank){
				upText = "Fill the tank!";
				upTextTimer = 5;
			}
		}
		if (slowerEnabled){
			currentSquadron.slowDown();
		}
	}

	private void loadNextLevel(int level) {
		squadronNumber = 0;
		player.skipGasChecker = false;
		init(level);
	}

	public void update(float deltaTime){
		if (gameWon) return;
		if (levelLost()){
			if (currentSquadron.isSquadronCleared()){
				loadNextSquadron();
			}
			if (slowerEnabled){
				slowerTimer -= deltaTime;
				//System.out.println(slowerTimer);
				if (slowerTimer <= 0) {
					slowerEnabled = false;
					cancelSlower();
				}
				
			}
			if (upTextTimer >= 0){
				upTextTimer -= deltaTime;
			}
			player.update(deltaTime);
			currentSquadron.update(deltaTime);
			updateBullets(deltaTime);
			checkCollisions();
			updatePowerUps(deltaTime);
			updateExplosions(deltaTime);
			freeBullets();
			freeExplosions();
			freePowerUps();
		} 
	}

	private void freePowerUps() {
		PowerUp item;
		for (int i = activePowerups.size; --i >= 0;){
			item = activePowerups.get(i);
			if (!item.isAlive()){
				activePowerups.removeIndex(i);
				powerUps.free(item);
			}
		}
	}

	private void updatePowerUps(float deltaTime) {
		for (PowerUp p : activePowerups){
			p.update(deltaTime);
		}
	}

	private void cancelSlower() {
		for (Bullet b : activeEnemyBullets){
			b.setSlowerFactor(1);
		}
		currentSquadron.cancelSLower();
	}

	private void freeExplosions() {
		Explosion item;
		for (int i = activeExplosions.size; --i >= 0;){
			item = activeExplosions.get(i);
			if (!item.isEnabled()){
				activeExplosions.removeIndex(i);
				explosions.free(item);
			}
		}
	}

	private void updateExplosions(float deltaTime) {
		for (Explosion explosion : activeExplosions){
			explosion.update(deltaTime);
		}
	}

	public boolean levelLost() {
		return player.isNotCompletelyDead();
	}

	private void updateBullets(float deltaTime) {
		for (Bullet bullet : activePlayerBullets){
			bullet.update(deltaTime);
		}
		for (Bullet bullet : activeEnemyBullets){
			bullet.update(deltaTime);
		}
	}

	private void checkCollisions() {
		for (GameObject o : currentSquadron.objects){
			if (o.isAlive()){
				for (Bullet b : activePlayerBullets){
					if (b.isEnabled()){
						if (o.testCollision(b)){
							b.reset();
							Assets.instance.sounds.hit.play();
							if (o.getPoints() == 50) player.gas += 5;
							createExplosion(o, false);
							player.addPoints(o.getPoints());
							if (ra.nextInt(100) <= powerupchange && o.spawnPowerup){
								Assets.instance.sounds.select.play();
								PowerUp pup = powerUps.obtain();
								Vector2 temp = new Vector2(o.getPosition().x, o.getPosition().y);
								pup.setPosition(temp);
								pup.alive = true;
								activePowerups.add(pup);
							}
							o.kill();
						}
					}
				}
			}
		}
		
		for (Bullet b : activeEnemyBullets){
			if (player.isAlive()){
				if (player.testCollision(b)){
					player.hit(1);
					b.reset();
					if (!player.isAlive()) createExplosion(player, true);
				}
			}
		}
		
		for (GameObject o : currentSquadron.objects){
			if (o.isAlive()){
				if (player.isAlive()){
					if (player.testCollision(o)){
						createExplosion(o, false);
						createExplosion(player, true);
						player.killByColl();
						o.kill();
					}
				}
			}
		}
		
		for (PowerUp up : activePowerups){
			if (player.isAlive() && up.isAlive()){
				if (player.testCollision(up)){
					Assets.instance.sounds.pup.play();
					up.powerUpPlayer(player);
					up.alive = false;
				}
			}
		}
	}

	private void createExplosion(GameObject o, boolean player) {
		Assets.instance.sounds.explosion.play();
		Explosion e = explosions.obtain();
		e.setPosition(o.getPosition().x, o.getPosition().y);
		e.setEnabled(true);
		e.setIsPlayer(player);
		activeExplosions.add(e);
	}

	public void addBullet(boolean player, float x, float y, int damage) {
		Assets.instance.sounds.fireLaser.play();
		Bullet bul = bullets.obtain();
		bul.setDamage(damage);
		bul.setPosition(x, y);
		bul.setEnabled(true);
		if (player){
			bul.setDirection(1);
			activePlayerBullets.add(bul);
		} else {
			bul.setDirection(-1);
			if (slowerEnabled) bul.setSlowerFactor(0.25f);
			activeEnemyBullets.add(bul);
			
		}
	}
	
	private void freeBullets(){
		Bullet item;
		for (int i = activePlayerBullets.size; --i >= 0;){
			item = activePlayerBullets.get(i);
			if (!item.isEnabled()){
				activePlayerBullets.removeIndex(i);
				bullets.free(item);
			}
		}
		for (int i = activeEnemyBullets.size; --i >= 0;){
			item = activeEnemyBullets.get(i);
			if (!item.isEnabled()){
				activeEnemyBullets.removeIndex(i);
				bullets.free(item);
			}
		}
	}
	
	public void draw(SpriteBatch batch){
		player.draw(batch);
		if (!gameWon){
			currentSquadron.draw(batch);
		}
		drawBullets(batch);
		drawExplosions(batch);
		drawPowerUps(batch);
		if (upTextTimer >= 0) Assets.instance.gui.font.draw(batch, upText, 15, 20);
	}

	private void drawPowerUps(SpriteBatch batch) {
		for (PowerUp up : activePowerups){
			up.draw(batch);
		}
	}

	private void drawExplosions(SpriteBatch batch) {
		for (Explosion explosion : activeExplosions){
			explosion.draw(batch);
		}
	}

	private void drawBullets(SpriteBatch batch) {
		for (Bullet bullet : activePlayerBullets){
			bullet.draw(batch);
		}
		for (Bullet bullet : activeEnemyBullets){
			bullet.draw(batch);
		}
	}

	public void activateSlower() {
		Assets.instance.sounds.pup.play();
		slowerEnabled = true;
		currentSquadron.slowDown();
		for (Bullet b : activeEnemyBullets){
			b.setSlowerFactor(0.25f);
		}
	}

	public int getPlayerPoints() {
		return player.getPoints();
	}

	public float getHeatPros() {
		return player.getHeatPros();
	}

	public float getFuelPros() {
		return player.getFuelPros();
	}

	public boolean isGameWon() {
		return gameWon;
	}


}
