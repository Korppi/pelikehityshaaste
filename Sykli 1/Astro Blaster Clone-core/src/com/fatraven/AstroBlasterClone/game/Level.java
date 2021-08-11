package com.fatraven.AstroBlasterClone.game;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;
import com.fatraven.AstroBlasterClone.game.objects.BackgroundStar;
import com.fatraven.AstroBlasterClone.game.objects.Bullet;
import com.fatraven.AstroBlasterClone.game.objects.Explosion;
import com.fatraven.AstroBlasterClone.game.objects.GameObject;
import com.fatraven.AstroBlasterClone.game.objects.Player;
import com.fatraven.AstroBlasterClone.game.objects.enemies.Asteroid;
import com.fatraven.AstroBlasterClone.game.objects.enemies.IEnemy;
import com.fatraven.AstroBlasterClone.game.objects.enemies.LevelOneEnemy;
import com.fatraven.AstroBlasterClone.game.objects.enemies.LevelTwoEnemy;
import com.fatraven.AstroBlasterClone.util.Constants;

public class Level {
	private Player player;
	private float respawnCd = 1.5f;
	private float currRs = 0;
	private int activeSquadron;
	private Array<IEnemy> enemies;
	private Array<BackgroundStar> stars;
	private final Array<Bullet> activePlayerBullets = new Array<Bullet>();
	private final Array<Bullet> activeEnemyBullets = new Array<Bullet>();
	private final Pool<Bullet> bullets = new Pool<Bullet>(){
		@Override
		protected Bullet newObject(){
			return new Bullet();
		}
	};
	private final Array<Explosion> activeExplosions = new Array<Explosion>();
	private final Pool<Explosion> explosions = new Pool<Explosion>(){
		@Override
		protected Explosion newObject(){
			return new Explosion();
		}
	};
	private boolean powerNotUsed;
	private float slowerTimer;
	private float slowerTimerCur;
	public boolean gameWon;
	
	public Level(){
		init();
	}
	
	private void init(){
		player = new Player(this);
		activeSquadron = 1;
		enemies = new Array<IEnemy>();
		initSquadon();
		powerNotUsed = true;
		slowerTimer = 15;
		slowerTimerCur = 0;
		gameWon = false;
		stars = new Array<BackgroundStar>();
		initStars();
	}
	
	private void initStars() {
		Random r = new Random();
		for (int i = 0; i < 20; i++){
			stars.add(new BackgroundStar(this, new Vector2(
					r.nextInt((int)Constants.VIEWPORT_WIDTH) + 1, 
					r.nextInt((int)Constants.VIEWPORT_HEIGHT + 20))));
		}
	}
	
	public void giveFuel(){
		player.giveFuel();
	}

	private void initSquadon() {
		switch (activeSquadron) {
		case 1:
			initSquadronOne();
			//Gdx.app.debug("tagit", "e " + activeEnemyBullets.size + " ja p " + activePlayerBullets.size);
			//Gdx.app.debug("tagit", "pool free: " + bullets.getFree());
			break;
		case 2:
			initSquadronTwo();
			//Gdx.app.debug("tagit", "e " + activeEnemyBullets.size + " ja p " + activePlayerBullets.size);
			//Gdx.app.debug("tagit", "pool free: " + bullets.getFree());
			break;
		case 3:
			initSquadronThree();
			//Gdx.app.debug("tagit", "e " + activeEnemyBullets.size + " ja p " + activePlayerBullets.size);
			//Gdx.app.debug("tagit", "pool free: " + bullets.getFree());
			break;
		case 4:
			initAsteroids();
			//Gdx.app.debug("tagit", "e " + activeEnemyBullets.size + " ja p " + activePlayerBullets.size);
			//Gdx.app.debug("tagit", "pool free: " + bullets.getFree());
			break;
		case 5:
			gameWon = true;
			break;
		default:
			
			break;
		}
		if (slowerTimerCur > 0){
			for (int i = 0; i < enemies.size; i++){
				enemies.get(i).setSlowTimer(0.2f);
			}
		}
	}
	
	private void initAsteroids() {
		enemies = new Array<IEnemy>();
		float minY = Constants.VIEWPORT_HEIGHT + 2;
		float maxY = minY + 60;
		Random r = new Random();
		int max = (int)Constants.VIEWPORT_WIDTH - 5;
		for (int i = 0; i < 50; i++){
			enemies.add(new Asteroid(this, 
					new Vector2(
							r.nextInt(max), 
							r.nextFloat() * (maxY - minY) + minY)));
			if (i % 2 == 0){
				enemies.get(i).addAnimation(new Animation(r.nextFloat() * (0.2f - 0.03f) + 0.03f, Assets.instance.asteroids.greyAsteroids));
				enemies.get(i).setPoints(9);
			} else {
				if (i % 5 != 0){
					enemies.get(i).addAnimation(new Animation(r.nextFloat() * (0.2f - 0.03f) + 0.03f, Assets.instance.asteroids.brownAsteroids));
					enemies.get(i).setPoints(10);
				} else {
					enemies.get(i).addAnimation(new Animation(r.nextFloat() * (0.2f - 0.03f) + 0.03f, Assets.instance.asteroids.redAsteroids));
					enemies.get(i).setPoints(15);
					Asteroid a = (Asteroid)enemies.get(i);
					a.isRed = true;
				}
			}
			enemies.get(i).setVelocity(r.nextInt(30) + 20);
			
			if (i % 10 == 0){
				minY += 60;
				maxY += 60;
				//Gdx.app.debug("Rrr", "moar min und maxen");
			}
			
		}
	}

	private void initSquadronThree(){
		enemies = new Array<IEnemy>();
		float x = 30;
		float y = 60;
		for (int i = 0; i < 3; i++){	
			enemies.add(new LevelOneEnemy(this, new Vector2(x, y)));
			enemies.get(i).addPoint(new Vector2(x + 5, y - 30 - (i * 10)));
			enemies.get(i).addPoint(new Vector2(x + 20, y - 20 - (i * 10)));
			enemies.get(i).addPoint(new Vector2(x - 25, y - 5 - (i * 10)));
			enemies.get(i).addPoint(new Vector2(x + 30, y - 10 - (i * 10)));
			enemies.get(i).addPoint(new Vector2(x + 17, y - 10 - (i * 10)));
			enemies.get(i).addPoint(new Vector2(x + 7, y - 15 - (i * 10)));
			enemies.get(i).addPoint(new Vector2(x + 18, y - 5 - (i * 10)));
			enemies.get(i).addPoint(new Vector2(x + 22, y - 8 - (i * 10)));
			enemies.get(i).addPoint(new Vector2(x + 7, y - 10 - (i * 10)));
			enemies.get(i).addPoint(new Vector2(x + 9, y - 12 - (i * 10)));
			enemies.get(i).addPoint(new Vector2(x + 13, y - 16 - (i * 10)));
			enemies.get(i).addPoint(new Vector2(x + 21, y - 26 - (i * 10)));
			enemies.get(i).addPoint(new Vector2(x + 7, y - 20 - (i * 10)));
			enemies.get(i).addPoint(new Vector2(x + 5, y - 13 - (i * 10)));
			enemies.get(i).setVelocity(80);
			enemies.get(i).setPoints(20);
			enemies.get(i).useAlternativeGraph(true);
			y += 10;
		}
	}

	private void initSquadronTwo() {
		enemies = new Array<IEnemy>();
		int x = -15;
		int y = 60;
		
		for (int i = 0; i < 14; i++){
			enemies.add(new LevelTwoEnemy(this, new Vector2(x, y)));
			x -= 8;
			if (i == 6){
				y = 50;
				x = -10;
			}
		}
	}

	private void initSquadronOne() {
		//Gdx.app.debug("level", "Adding points");
		int x = 5;
		int y = 100;
		enemies = new Array<IEnemy>();
		
		for (int i = 0; i < 17; i++){
			enemies.add(new LevelOneEnemy(this, new Vector2(x, y)));
			enemies.get(i).addPoint(new Vector2(x, y - 80));
			enemies.get(i).addPoint(new Vector2(x + 15, y - 80));
			enemies.get(i).addPoint(new Vector2(x + 9, y - 70));
			enemies.get(i).addPoint(new Vector2(x - 5, y - 70));
			enemies.get(i).addPoint(new Vector2(x + 10, y - 65));
			enemies.get(i).calculateMovement();
			x += 10;
			if (i == 5){
				x = 10;
				y = 110;
			}
			if (i == 10){
				x = 5;
				y = 120;
			}
		}
	}

	private void freeBullets(){
		Bullet item;
		for (int i = activePlayerBullets.size; --i >= 0;){
			item = activePlayerBullets.get(i);
			if (!item.isAlive()){
				activePlayerBullets.removeIndex(i);
				bullets.free(item);
			}
		}
		for (int i = activeEnemyBullets.size; --i >= 0;){
			item = activeEnemyBullets.get(i);
			if (!item.isAlive()){
				activeEnemyBullets.removeIndex(i);
				bullets.free(item);
			}
		}
	}
	
	private void freeExplosions(){
		Explosion item;
		for (int i = activeExplosions.size; --i >= 0;){
			item = activeExplosions.get(i);
			if (item.isEnded()){
				activeExplosions.removeIndex(i);
				explosions.free(item);
			}
		}
	}
	
	public boolean isWaveDead(){
		for (int i = 0; i < enemies.size; i++){
			if (enemies.get(i).isAlive()) return false;
		}
		return true;
	}
	
	public void fireBullet(boolean player, float x, float y, int dmg){
		Assets.instance.sounds.laser.play();
		Bullet item = bullets.obtain();
		item.init(x,y);
		if (player){
			item.setDamage(dmg);
			activePlayerBullets.add(item);
		} else {
			item.setVelocity(-30);
			if (slowerTimerCur > 0){
				item.setSlowerTimer(0.2f);
			}
			activeEnemyBullets.add(item);
		}
	}

	public void update(float deltaTime) {
		slowerTimerCur -= deltaTime;
		if (slowerTimerCur < -20) slowerTimerCur = 0;
		if (slowerTimerCur < 0 && !powerNotUsed ){
			//Gdx.app.debug("loooool", "not slowed!");
			if (enemies.get(0).getSlowerTimer() != 1){
				for (int i = 0; i < enemies.size; i++){
					enemies.get(i).setSlowTimer(1.0f);
				}
				for (int i = 0; i < activeEnemyBullets.size; i++){
					activeEnemyBullets.get(i).setSlowerTimer(1.0f);
				}
			}
		}
		if (player.isAlive() && !player.isStunned()){
			player.update(deltaTime);
			if (Gdx.input.isKeyJustPressed(Keys.CONTROL_LEFT) && 
					powerNotUsed){
				powerNotUsed = false;
				slowerTimerCur = slowerTimer;
				//Gdx.app.debug("loooool", "slowed!");
				for (int i = 0; i < enemies.size; i++){
					enemies.get(i).setSlowTimer(0.25f);
				}
				for (int i = 0; i < activeEnemyBullets.size; i++){
					activeEnemyBullets.get(i).setSlowerTimer(0.2f);
				}
			}
		} else if (player.isAlive() && player.isStunned()){
			currRs += deltaTime;
			if (currRs > respawnCd) {
				player.resurrect();
				currRs = 0;
			}
		}
		for (int i = 0; i < enemies.size; i++){
			if (enemies.get(i).isAlive()){
				enemies.get(i).update(deltaTime);
			}
		}
		for (int i = 0; i < activePlayerBullets.size; i++){
			activePlayerBullets.get(i).update(deltaTime);
		}
		for (int i = 0; i < activeEnemyBullets.size; i++){
			activeEnemyBullets.get(i).update(deltaTime);
		}
		for (int i = 0; i < stars.size; i++){
			stars.get(i).update(deltaTime);
		}
		for (int i = 0; i < activeExplosions.size; i++){
			activeExplosions.get(i).update(deltaTime);
		}
		checkCollisions();
		freeBullets();
		freeExplosions();
		if (isWaveDead()){
			activeSquadron++;
			initSquadon();
		}
	}

	private void checkCollisions() {
		for (int i = 0; i < enemies.size; i++){
			if (enemies.get(i).isAlive() && player.isAlive() && !player.isStunned()){
				if (player.testCollision((GameObject)enemies.get(i))){
					player.kill();
					enemies.get(i).kill();
					Explosion e = explosions.obtain();
					e.setPosition(player.position.x, player.position.y);
					e.init(Assets.instance.explosions.explosions);
					e.setScale(true);
					activeExplosions.add(e);
					e = explosions.obtain();
					e.setPosition(enemies.get(i).getPosition().x, enemies.get(i).getPosition().y);
					e.init(Assets.instance.explosions.explosions);
					activeExplosions.add(e);
				}
			}
			if (enemies.get(i).isAlive()){
				for (int j = 0; j < activePlayerBullets.size; j++){
					if (activePlayerBullets.get(j).testCollision((GameObject) enemies.get(i))){
						enemies.get(i).kill();
						activePlayerBullets.get(j).reset();
						player.addPoints(enemies.get(i).getPoints());
						Explosion e = explosions.obtain();
						e.setPosition(enemies.get(i).getPosition().x, enemies.get(i).getPosition().y);
						e.init(Assets.instance.explosions.explosions);
						activeExplosions.add(e);
					}
				}
			}			
		}
		for (int i = 0; i < activeEnemyBullets.size; i++){
			if (player.isAlive() && !player.isStunned()){
				if (player.testCollision(activeEnemyBullets.get(i))){
					player.hitByLaser();
					activeEnemyBullets.get(i).reset();
					if (player.isStunned() || !playerHasLives()){
						Explosion e = explosions.obtain();
						e.setPosition(player.position.x, player.position.y);
						e.init(Assets.instance.explosions.explosions);
						activeExplosions.add(e);
						e.setScale(true);
					}
				}
			}
			
		}
	}

	public void render(SpriteBatch batch) {
		for (int i = 0; i < stars.size; i++){
			stars.get(i).render(batch);
		}
		if (player.isAlive() && !player.isStunned()){
			player.render(batch);
		}
		for (int i = 0; i < enemies.size; i++){
			if (enemies.get(i).isAlive()){
				enemies.get(i).render(batch);
			}
		}
		for (int i = 0; i < activeExplosions.size; i++){
			activeExplosions.get(i).render(batch);
		}
		for (int i = 0; i < activePlayerBullets.size; i++){
			activePlayerBullets.get(i).render(batch);
		}
		for (int i = 0; i < activeEnemyBullets.size; i++){
			activeEnemyBullets.get(i).render(batch);
		}
	}

	public boolean playerHasLives() {
		if (player.isAlive()) return true;
		return false;
	}

	public int getPlayerScore() {
		return player.getPoints();
	}

	public void renderPlayer(Camera cam) {
		player.shapeRender(cam);
		enemies.get(0).shapeRender(cam);
	}

	public float getPlayerHeatPros() {
		return player.getOverHeat() / player.getOverheatMax();
	}

	public float getPlayerFuelPros() {
		return player.getFuel() / player.getFuelMax();
	}	
}
